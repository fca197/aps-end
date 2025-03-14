package com.olivia.peanut.base.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.base.api.entity.baseUserResource.*;
import com.olivia.peanut.base.mapper.BaseUserResourceMapper;
import com.olivia.peanut.base.model.BaseUserResource;
import com.olivia.peanut.base.service.BaseTableHeaderService;
import com.olivia.peanut.base.service.BaseUserResourceService;
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
 * 用户角色资源表(BaseUserResource)表服务实现类
 *
 * @author peanut
 * @since 2024-08-09 16:26:40
 */
@Service("baseUserResourceService")
@Transactional
public class BaseUserResourceServiceImpl extends MPJBaseServiceImpl<BaseUserResourceMapper, BaseUserResource> implements BaseUserResourceService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;


  public @Override BaseUserResourceQueryListRes queryList(BaseUserResourceQueryListReq req) {

    MPJLambdaWrapper<BaseUserResource> q = getWrapper(req.getData());
    List<BaseUserResource> list = this.list(q);

    List<BaseUserResourceDto> dataList = list.stream().map(t -> $.copy(t, BaseUserResourceDto.class)).collect(Collectors.toList());
    ((BaseUserResourceService) AopContext.currentProxy()).setName(dataList);
    return new BaseUserResourceQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<BaseUserResourceExportQueryPageListInfoRes> queryPageList(BaseUserResourceExportQueryPageListReq req) {

    DynamicsPage<BaseUserResource> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<BaseUserResource> q = getWrapper(req.getData());
    List<BaseUserResourceExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<BaseUserResource> list = this.page(page, q);
      IPage<BaseUserResourceExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, BaseUserResourceExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), BaseUserResourceExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<BaseUserResourceExportQueryPageListInfoRes> listInfoRes = $.copyList(records, BaseUserResourceExportQueryPageListInfoRes.class);
    ((BaseUserResourceService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends BaseUserResourceDto> list) {

    //   setNameService.setName(list, SetNamePojoUtils.FACTORY, SetNamePojoUtils.OP_USER_NAME);

  }


  private MPJLambdaWrapper<BaseUserResource> getWrapper(BaseUserResourceDto obj) {
    MPJLambdaWrapper<BaseUserResource> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getUserId()), BaseUserResource::getUserId, obj.getUserId())
          .eq(Objects.nonNull(obj.getResourceId()), BaseUserResource::getResourceId, obj.getResourceId())

      ;
    }
    q.orderByDesc(BaseUserResource::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<BaseUserResource> page) {

    tableHeaderService.listByBizKey(page, "BaseUserResourceService#queryPageList");

  }


}

