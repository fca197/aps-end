package com.olivia.peanut.base.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.base.api.entity.baseUserRoleGroup.*;
import com.olivia.peanut.base.mapper.BaseUserRoleGroupMapper;
import com.olivia.peanut.base.model.BaseUserRoleGroup;
import com.olivia.peanut.base.service.BaseTableHeaderService;
import com.olivia.peanut.base.service.BaseUserRoleGroupService;
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
 * 用户角色组表(BaseUserRoleGroup)表服务实现类
 *
 * @author peanut
 * @since 2024-07-31 14:36:04
 */
@Service("baseUserRoleGroupService")
@Transactional
public class BaseUserRoleGroupServiceImpl extends MPJBaseServiceImpl<BaseUserRoleGroupMapper, BaseUserRoleGroup> implements BaseUserRoleGroupService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;


  public @Override BaseUserRoleGroupQueryListRes queryList(BaseUserRoleGroupQueryListReq req) {

    MPJLambdaWrapper<BaseUserRoleGroup> q = getWrapper(req.getData());
    List<BaseUserRoleGroup> list = this.list(q);

    List<BaseUserRoleGroupDto> dataList = list.stream().map(t -> $.copy(t, BaseUserRoleGroupDto.class)).collect(Collectors.toList());
    ((BaseUserRoleGroupService) AopContext.currentProxy()).setName(dataList);
    return new BaseUserRoleGroupQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<BaseUserRoleGroupExportQueryPageListInfoRes> queryPageList(BaseUserRoleGroupExportQueryPageListReq req) {

    DynamicsPage<BaseUserRoleGroup> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<BaseUserRoleGroup> q = getWrapper(req.getData());
    List<BaseUserRoleGroupExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<BaseUserRoleGroup> list = this.page(page, q);
      IPage<BaseUserRoleGroupExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, BaseUserRoleGroupExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), BaseUserRoleGroupExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<BaseUserRoleGroupExportQueryPageListInfoRes> listInfoRes = $.copyList(records, BaseUserRoleGroupExportQueryPageListInfoRes.class);
    ((BaseUserRoleGroupService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends BaseUserRoleGroupDto> list) {

    //   setNameService.setName(list, SetNamePojoUtils.FACTORY, SetNamePojoUtils.OP_USER_NAME);

  }


  private MPJLambdaWrapper<BaseUserRoleGroup> getWrapper(BaseUserRoleGroupDto obj) {
    MPJLambdaWrapper<BaseUserRoleGroup> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getUserId()), BaseUserRoleGroup::getUserId, obj.getUserId())
          .eq(Objects.nonNull(obj.getRoleGroupId()), BaseUserRoleGroup::getRoleGroupId, obj.getRoleGroupId())

      ;
    }
    q.orderByDesc(BaseUserRoleGroup::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<BaseUserRoleGroup> page) {

    tableHeaderService.listByBizKey(page, "BaseUserRoleGroupService#queryPageList");

  }


}

