package com.olivia.peanut.base.service.impl;

import org.springframework.aop.framework.AopContext;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import jakarta.annotation.Resource;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.olivia.peanut.base.mapper.BaseAppResourceMapper;
import com.olivia.peanut.base.model.BaseAppResource;
import com.olivia.peanut.base.service.BaseAppResourceService;
import cn.hutool.core.collection.CollUtil;
//import com.olivia.peanut.base.service.BaseTableHeaderService;
import com.olivia.peanut.portal.service.BaseTableHeaderService;
import com.olivia.peanut.base.api.entity.baseAppResource.*;
import com.olivia.peanut.util.SetNamePojoUtils;
import com.olivia.sdk.service.SetNameService;

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

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getAppId()), BaseAppResource::getAppId, obj.getAppId())
          .eq(Objects.nonNull(obj.getResourceId()), BaseAppResource::getResourceId, obj.getResourceId())
      ;
    }
    q.orderByDesc(BaseAppResource::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<BaseAppResource> page) {

    tableHeaderService.listByBizKey(page, "BaseAppResourceService#queryPageList");

  }


}

