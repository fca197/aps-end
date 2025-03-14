package com.olivia.peanut.base.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.base.api.entity.baseRoleGroupResource.*;
import com.olivia.peanut.base.mapper.BaseRoleGroupResourceMapper;
import com.olivia.peanut.base.model.BaseRoleGroupResource;
import com.olivia.peanut.base.service.BaseRoleGroupResourceService;
import com.olivia.peanut.base.service.BaseTableHeaderService;
import com.olivia.sdk.service.SetNameService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.annotation.Resource;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 角色组资源表(BaseRoleGroupResource)表服务实现类
 *
 * @author peanut
 * @since 2024-08-09 15:42:34
 */
@Service("baseRoleGroupResourceService")
@Transactional
public class BaseRoleGroupResourceServiceImpl extends MPJBaseServiceImpl<BaseRoleGroupResourceMapper, BaseRoleGroupResource> implements BaseRoleGroupResourceService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;


  public @Override BaseRoleGroupResourceQueryListRes queryList(BaseRoleGroupResourceQueryListReq req) {

    MPJLambdaWrapper<BaseRoleGroupResource> q = getWrapper(req.getData());
    List<BaseRoleGroupResource> list = this.list(q);

    List<BaseRoleGroupResourceDto> dataList = list.stream().map(t -> $.copy(t, BaseRoleGroupResourceDto.class)).collect(Collectors.toList());
    ((BaseRoleGroupResourceService) AopContext.currentProxy()).setName(dataList);
    return new BaseRoleGroupResourceQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<BaseRoleGroupResourceExportQueryPageListInfoRes> queryPageList(BaseRoleGroupResourceExportQueryPageListReq req) {

    DynamicsPage<BaseRoleGroupResource> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<BaseRoleGroupResource> q = getWrapper(req.getData());
    List<BaseRoleGroupResourceExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<BaseRoleGroupResource> list = this.page(page, q);
      IPage<BaseRoleGroupResourceExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, BaseRoleGroupResourceExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), BaseRoleGroupResourceExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<BaseRoleGroupResourceExportQueryPageListInfoRes> listInfoRes = $.copyList(records, BaseRoleGroupResourceExportQueryPageListInfoRes.class);
    ((BaseRoleGroupResourceService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends BaseRoleGroupResourceDto> list) {

    //   setNameService.setName(list, SetNamePojoUtils.FACTORY, SetNamePojoUtils.OP_USER_NAME);

  }


  private MPJLambdaWrapper<BaseRoleGroupResource> getWrapper(BaseRoleGroupResourceDto obj) {
    MPJLambdaWrapper<BaseRoleGroupResource> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getRoleGroupId()), BaseRoleGroupResource::getRoleGroupId, obj.getRoleGroupId())
          .eq(Objects.nonNull(obj.getResourceId()), BaseRoleGroupResource::getResourceId, obj.getResourceId())

      ;
    }
    q.orderByDesc(BaseRoleGroupResource::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<BaseRoleGroupResource> page) {

    tableHeaderService.listByBizKey(page, "BaseRoleGroupResourceService#queryPageList");

  }


}

