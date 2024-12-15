package com.olivia.peanut.aps.service.impl;

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
import com.olivia.peanut.aps.mapper.ApsBomSupplierMapper;
import com.olivia.peanut.aps.model.ApsBomSupplier;
import com.olivia.peanut.aps.service.ApsBomSupplierService;
import cn.hutool.core.collection.CollUtil;
//import com.olivia.peanut.aps.service.BaseTableHeaderService;
import com.olivia.peanut.portal.service.BaseTableHeaderService;
import com.olivia.peanut.aps.api.entity.apsBomSupplier.*;
import com.olivia.peanut.util.SetNamePojoUtils;
import com.olivia.sdk.service.SetNameService;

/**
 * 供应商表(ApsBomSupplier)表服务实现类
 *
 * @author makejava
 * @since 2024-12-15 14:39:46
 */
@Service("apsBomSupplierService")
@Transactional
public class ApsBomSupplierServiceImpl extends MPJBaseServiceImpl<ApsBomSupplierMapper, ApsBomSupplier> implements ApsBomSupplierService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;


  public @Override ApsBomSupplierQueryListRes queryList(ApsBomSupplierQueryListReq req) {

    MPJLambdaWrapper<ApsBomSupplier> q = getWrapper(req.getData());
    List<ApsBomSupplier> list = this.list(q);

    List<ApsBomSupplierDto> dataList = list.stream().map(t -> $.copy(t, ApsBomSupplierDto.class)).collect(Collectors.toList());
    ((ApsBomSupplierService) AopContext.currentProxy()).setName(dataList);
    return new ApsBomSupplierQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsBomSupplierExportQueryPageListInfoRes> queryPageList(ApsBomSupplierExportQueryPageListReq req) {

    DynamicsPage<ApsBomSupplier> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsBomSupplier> q = getWrapper(req.getData());
    List<ApsBomSupplierExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsBomSupplier> list = this.page(page, q);
      IPage<ApsBomSupplierExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsBomSupplierExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsBomSupplierExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作 

    List<ApsBomSupplierExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsBomSupplierExportQueryPageListInfoRes.class);
    ((ApsBomSupplierService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends ApsBomSupplierDto> list) {

    //   setNameService.setName(list, SetNamePojoUtils.FACTORY, SetNamePojoUtils.OP_USER_NAME);

  }


  @SuppressWarnings("unchecked")
  private MPJLambdaWrapper<ApsBomSupplier> getWrapper(ApsBomSupplierDto obj) {
    MPJLambdaWrapper<ApsBomSupplier> q = new MPJLambdaWrapper<>();


    $.lambdaQueryWrapper(q, obj, ApsBomSupplier.class
        // 查询条件
        , ApsBomSupplier::getBomSupplierName //
        , ApsBomSupplier::getBomSupplierCode //
        , ApsBomSupplier::getBomSupplierPhone //
        , ApsBomSupplier::getBomSupplierTel //
        , ApsBomSupplier::getBomSupplierEmail //
        , ApsBomSupplier::getProvinceCode //
        , ApsBomSupplier::getCityCode //
        , ApsBomSupplier::getAreaCode //
        , ApsBomSupplier::getBomSupplierAddr //
        , ApsBomSupplier::getBomSupplierRemark //
        , ApsBomSupplier::getSupplierStatus //
    );


    q.orderByDesc(ApsBomSupplier::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsBomSupplier> page) {

    tableHeaderService.listByBizKey(page, "ApsBomSupplierService#queryPageList");

  }


}

