package com.olivia.peanut.base.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.base.api.entity.baseAppResource.*;
import com.olivia.peanut.base.mapper.BaseAppResourceMapper;
import com.olivia.peanut.base.model.*;
import com.olivia.peanut.base.service.*;
import com.olivia.sdk.filter.LoginUserContext;
import com.olivia.sdk.service.SetNameService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.BaseEntity;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.RunUtils;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 资源(BaseAppResource)表服务实现类
 *
 * @author peanut
 * @since 2024-08-06 17:30:28
 */
@Service("baseAppResourceService")
@Transactional
public class BaseAppResourceServiceImpl extends MPJBaseServiceImpl<BaseAppResourceMapper, BaseAppResource> implements BaseAppResourceService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;
  @Resource
  BaseAppService baseAppService;
  @Resource
  BaseRoleResourceService baseRoleResourceService;
  @Resource
  BaseResourceService baseResourceService;

  // 以下为私有对象封装
  @Resource
  BaseRoleGroupResourceService baseRoleGroupResourceService;
  @Resource
  BaseUserRoleService baseUserRoleService;
  @Resource
  BaseUserRoleGroupService baseUserRoleGroupService;

  public @Override BaseAppResourceQueryListRes queryList(BaseAppResourceQueryListReq req) {

    MPJLambdaWrapper<BaseAppResource> q = getWrapper(req.getData());
    List<BaseAppResource> list = this.list(q);

    List<BaseAppResourceDto> dataList = list.stream().map(t -> $.copy(t, BaseAppResourceDto.class)).collect(Collectors.toList());
    ((BaseAppResourceService) AopContext.currentProxy()).setName(dataList);
    return new BaseAppResourceQueryListRes().setDataList(dataList);
  }

  public @Override DynamicsPage<BaseAppResourceExportQueryPageListInfoRes> queryPageList(BaseAppResourceExportQueryPageListReq req) {

    DynamicsPage<BaseAppResource> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<BaseAppResource> q = getWrapper(req.getData());
    List<BaseAppResourceExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<BaseAppResource> list = this.page(page, q);
      IPage<BaseAppResourceExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, BaseAppResourceExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), BaseAppResourceExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<BaseAppResourceExportQueryPageListInfoRes> listInfoRes = $.copyList(records, BaseAppResourceExportQueryPageListInfoRes.class);
    ((BaseAppResourceService) AopContext.currentProxy()).setName(new ArrayList<>(listInfoRes));

    return DynamicsPage.init(page, listInfoRes);
  }

  public @Override void setName(List<? extends BaseAppResourceDto> list) {
    if (CollUtil.isEmpty(list)) {
      return;
    }
    if (!Boolean.TRUE.equals(LoginUserContext.getLoginUser().isAdmin())) {

      Long id = LoginUserContext.getLoginUser().getId();
      Set<Long> resourceIdSet = Collections.synchronizedSet(new HashSet<>());

      List<Runnable> runnableList = getRunnableList(id, resourceIdSet);

      RunUtils.run("获取用户角色角色组菜单 " + id, runnableList);

      list.removeIf(t -> !resourceIdSet.contains(t.getResourceId()));

    } else {
      BaseAppResourceDto baseAppResourceDto = list.getFirst();

      if (baseAppResourceDto instanceof BaseAppResourceExportQueryPageListInfoRes) {
        Set<Long> idSet = list.parallelStream().map(BaseAppResourceDto::getResourceId).collect(Collectors.toSet());
        Map<Long, BaseResource> resourceMap = this.baseResourceService.listByIds(idSet).stream().collect(Collectors.toMap(BaseEntity::getId, Function.identity()));
        BaseResource defaultValue = new BaseResource();
        list.forEach(t -> {
          BaseResource resource = resourceMap.getOrDefault(t.getResourceId(), defaultValue);
          ((BaseAppResourceExportQueryPageListInfoRes) t).setResourceName(resource.getResourceName())
              .setSortIndex(resource.getSortIndex())
              .setResourceUrl(resource.getResourceUrl()).setResourceName(resource.getResourceName()).setParentId(resource.getParentId());
        });
      }

//      setNameService.setName(list,//
//          SetNamePojoUtils.getSetNamePojo(BaseResourceService.class, "resourceUrl", "resourceId", "resourceUrl"),
//          SetNamePojoUtils.getSetNamePojo(BaseResourceService.class, "resourceName", "resourceId", "resourceName"),
////          SetNamePojoUtils.getSetNamePojo(BaseResourceService.class, "id", "resourceId", "id"),
//          SetNamePojoUtils.getSetNamePojo(BaseResourceService.class, "parentId", "resourceId", "parentId"),
//          SetNamePojoUtils.getSetNamePojo(BaseResourceService.class, "resourceComment", "resourceId", "resourceComment"));
    }

  }

  private MPJLambdaWrapper<BaseAppResource> getWrapper(BaseAppResourceDto obj) {
    MPJLambdaWrapper<BaseAppResource> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      if (Objects.isNull(obj.getAppId()) && StringUtils.isNotBlank(obj.getAppCode())) {
        BaseApp one = baseAppService.getOne(new LambdaQueryWrapper<BaseApp>().eq(BaseApp::getAppCode, obj.getAppCode()));
        if (Objects.nonNull(one)) {
          obj.setAppId(one.getId());
        }
      }
      q.eq(Objects.nonNull(obj.getAppId()), BaseAppResource::getAppId, obj.getAppId())
          .eq(Objects.nonNull(obj.getResourceId()), BaseAppResource::getResourceId, obj.getResourceId());
    }
    q.orderByDesc(BaseAppResource::getId);
    return q;

  }

  private List<Runnable> getRunnableList(Long id, Set<Long> resourceIdSet) {
    List<Runnable> runnableList = new ArrayList<>();
    runnableList.add(() -> {
      Set<Long> userRoleGroupIdSet = baseUserRoleGroupService.list(
              new LambdaQueryWrapper<BaseUserRoleGroup>().select(BaseUserRoleGroup::getRoleGroupId).eq(BaseUserRoleGroup::getUserId, id)).stream()
          .map(BaseUserRoleGroup::getRoleGroupId).collect(Collectors.toSet());
      if (CollUtil.isNotEmpty(userRoleGroupIdSet)) {
        resourceIdSet.addAll(baseRoleGroupResourceService.list(
                new LambdaQueryWrapper<BaseRoleGroupResource>().in(BaseRoleGroupResource::getRoleGroupId, userRoleGroupIdSet).
                    select(BaseRoleGroupResource::getResourceId)).stream()
            .map(BaseRoleGroupResource::getResourceId).toList());
      }
    });
    runnableList.add(() -> {

      Set<Long> userRoleIdSet = baseUserRoleService.list(new LambdaQueryWrapper<BaseUserRole>().select(BaseUserRole::getRoleId).eq(BaseUserRole::getUserId, id)).stream()
          .map(BaseUserRole::getRoleId).collect(Collectors.toSet());
      if (CollUtil.isNotEmpty(userRoleIdSet)) {
        resourceIdSet.addAll(
            baseRoleResourceService.list(new LambdaQueryWrapper<BaseRoleResource>()
                    .in(BaseRoleResource::getRoleId, userRoleIdSet).select(BaseRoleResource::getResourceId))
                .stream().map(BaseRoleResource::getResourceId).toList());
      }
    });
    return runnableList;
  }

  private void setQueryListHeader(DynamicsPage<BaseAppResource> page) {

    tableHeaderService.listByBizKey(page, "BaseAppResourceService#queryPageList");

  }


}

