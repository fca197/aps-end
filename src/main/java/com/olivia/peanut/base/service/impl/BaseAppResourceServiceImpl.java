package com.olivia.peanut.base.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.base.api.entity.baseAppResource.*;
import com.olivia.peanut.base.mapper.BaseAppResourceMapper;
import com.olivia.peanut.base.model.BaseAppResource;
import com.olivia.peanut.base.service.BaseAppResourceService;
import com.olivia.peanut.portal.service.BaseTableHeaderService;
import com.olivia.sdk.filter.LoginUserContext;
import com.olivia.sdk.service.SetNameService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 资源(BaseAppResource)表服务实现类
 *
 * @author peanut
 * @since 2024-08-05 11:19:00
 */
@Service("baseAppResourceService")
@Transactional
public class BaseAppResourceServiceImpl extends MPJBaseServiceImpl<BaseAppResourceMapper, BaseAppResource> implements BaseAppResourceService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;


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
    ((BaseAppResourceService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends BaseAppResourceDto> list) {

    //   setNameService.setName(list, SetNamePojoUtils.FACTORY, SetNamePojoUtils.OP_USER_NAME);

  }


  private MPJLambdaWrapper<BaseAppResource> getWrapper(BaseAppResourceDto obj) {
    MPJLambdaWrapper<BaseAppResource> q = new MPJLambdaWrapper<>();

    String useAppKey = LoginUserContext.getLoginUser().getUseAppKey();
    if (Objects.nonNull(obj)) {
      obj.setAppCode(useAppKey);
      q
          .eq(Objects.nonNull(obj.getAppId()), BaseAppResource::getAppId, obj.getAppId())
          .eq(StringUtils.isNoneBlank(obj.getAppCode()), BaseAppResource::getAppCode, obj.getAppCode())
          .eq(StringUtils.isNoneBlank(obj.getResourceCode()), BaseAppResource::getResourceCode, obj.getResourceCode())
          .eq(StringUtils.isNoneBlank(obj.getResourceName()), BaseAppResource::getResourceName, obj.getResourceName())
          .eq(StringUtils.isNoneBlank(obj.getResourceUrl()), BaseAppResource::getResourceUrl, obj.getResourceUrl())
          .eq(StringUtils.isNoneBlank(obj.getResourceIcon()), BaseAppResource::getResourceIcon, obj.getResourceIcon())
          .eq(StringUtils.isNoneBlank(obj.getResourceType()), BaseAppResource::getResourceType, obj.getResourceType())
          .eq(Objects.nonNull(obj.getIsButton()), BaseAppResource::getIsButton, obj.getIsButton())
          .eq(Objects.nonNull(obj.getParentId()), BaseAppResource::getParentId, obj.getParentId())
          .eq(StringUtils.isNoneBlank(obj.getPath()), BaseAppResource::getPath, obj.getPath())

      ;
    } else {
      q.eq(BaseAppResource::getAppCode, useAppKey);
    }
//    q.orderByDesc(BaseAppResource::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<BaseAppResource> page) {

    tableHeaderService.listByBizKey(page, "BaseAppResourceService#queryPageList");

  }


}

