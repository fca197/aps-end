package com.olivia.peanut.base.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.base.api.entity.baseRoleResource.*;
import com.olivia.peanut.base.mapper.BaseRoleResourceMapper;
import com.olivia.peanut.base.model.BaseRoleResource;
import com.olivia.peanut.base.service.BaseRoleResourceService;
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
 * 角色资源表(BaseRoleResource)表服务实现类
 *
 * @author peanut
 * @since 2024-08-09 15:42:36
 */
@Service("baseRoleResourceService")
@Transactional
public class BaseRoleResourceServiceImpl extends MPJBaseServiceImpl<BaseRoleResourceMapper, BaseRoleResource> implements BaseRoleResourceService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;


  public @Override BaseRoleResourceQueryListRes queryList(BaseRoleResourceQueryListReq req) {

    MPJLambdaWrapper<BaseRoleResource> q = getWrapper(req.getData());
    List<BaseRoleResource> list = this.list(q);

    List<BaseRoleResourceDto> dataList = list.stream().map(t -> $.copy(t, BaseRoleResourceDto.class)).collect(Collectors.toList());
    ((BaseRoleResourceService) AopContext.currentProxy()).setName(dataList);
    return new BaseRoleResourceQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<BaseRoleResourceExportQueryPageListInfoRes> queryPageList(BaseRoleResourceExportQueryPageListReq req) {

    DynamicsPage<BaseRoleResource> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<BaseRoleResource> q = getWrapper(req.getData());
    List<BaseRoleResourceExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<BaseRoleResource> list = this.page(page, q);
      IPage<BaseRoleResourceExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, BaseRoleResourceExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), BaseRoleResourceExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<BaseRoleResourceExportQueryPageListInfoRes> listInfoRes = $.copyList(records, BaseRoleResourceExportQueryPageListInfoRes.class);
    ((BaseRoleResourceService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends BaseRoleResourceDto> list) {

    //   setNameService.setName(list, SetNamePojoUtils.FACTORY, SetNamePojoUtils.OP_USER_NAME);

  }


  private MPJLambdaWrapper<BaseRoleResource> getWrapper(BaseRoleResourceDto obj) {
    MPJLambdaWrapper<BaseRoleResource> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getRoleId()), BaseRoleResource::getRoleId, obj.getRoleId())
          .eq(Objects.nonNull(obj.getResourceId()), BaseRoleResource::getResourceId, obj.getResourceId())

      ;
    }
    q.orderByDesc(BaseRoleResource::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<BaseRoleResource> page) {

    tableHeaderService.listByBizKey(page, "BaseRoleResourceService#queryPageList");

  }


}

