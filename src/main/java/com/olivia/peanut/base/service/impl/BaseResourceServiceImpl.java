package com.olivia.peanut.base.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.base.api.entity.baseResource.*;
import com.olivia.peanut.base.mapper.BaseResourceMapper;
import com.olivia.peanut.base.model.BaseResource;
import com.olivia.peanut.base.service.BaseResourceService;
import com.olivia.peanut.base.service.BaseTableHeaderService;
import com.olivia.sdk.service.SetNameService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 资源(BaseResource)表服务实现类
 *
 * @author peanut
 * @since 2024-08-06 17:29:01
 */
@Service("baseResourceService")
@Transactional
public class BaseResourceServiceImpl extends MPJBaseServiceImpl<BaseResourceMapper, BaseResource> implements BaseResourceService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;


  public @Override BaseResourceQueryListRes queryList(BaseResourceQueryListReq req) {

    MPJLambdaWrapper<BaseResource> q = getWrapper(req.getData());
    List<BaseResource> list = this.list(q);

    List<BaseResourceDto> dataList = list.stream().map(t -> $.copy(t, BaseResourceDto.class)).collect(Collectors.toList());
    ((BaseResourceService) AopContext.currentProxy()).setName(dataList);
    return new BaseResourceQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<BaseResourceExportQueryPageListInfoRes> queryPageList(BaseResourceExportQueryPageListReq req) {

    DynamicsPage<BaseResource> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<BaseResource> q = getWrapper(req.getData());
    List<BaseResourceExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<BaseResource> list = this.page(page, q);
      IPage<BaseResourceExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, BaseResourceExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), BaseResourceExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<BaseResourceExportQueryPageListInfoRes> listInfoRes = $.copyList(records, BaseResourceExportQueryPageListInfoRes.class);
    ((BaseResourceService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends BaseResourceDto> list) {

    //   setNameService.setName(list, SetNamePojoUtils.FACTORY, SetNamePojoUtils.OP_USER_NAME);

  }


  private MPJLambdaWrapper<BaseResource> getWrapper(BaseResourceDto obj) {
    MPJLambdaWrapper<BaseResource> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(StringUtils.isNoneBlank(obj.getResourceCode()), BaseResource::getResourceCode, obj.getResourceCode())
          .like(StringUtils.isNoneBlank(obj.getResourceName()), BaseResource::getResourceName, obj.getResourceName())
          .eq(StringUtils.isNoneBlank(obj.getResourceUrl()), BaseResource::getResourceUrl, obj.getResourceUrl())
          .eq(StringUtils.isNoneBlank(obj.getResourceIcon()), BaseResource::getResourceIcon, obj.getResourceIcon())
          .eq(StringUtils.isNoneBlank(obj.getResourceType()), BaseResource::getResourceType, obj.getResourceType())
          .eq(Objects.nonNull(obj.getIsButton()), BaseResource::getIsButton, obj.getIsButton())
          .eq(Objects.nonNull(obj.getParentId()), BaseResource::getParentId, obj.getParentId())
          .eq(StringUtils.isNoneBlank(obj.getPath()), BaseResource::getPath, obj.getPath())

      ;
    }
    q.orderByDesc(BaseResource::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<BaseResource> page) {

    tableHeaderService.listByBizKey(page, "BaseResourceService#queryPageList");

  }


}

