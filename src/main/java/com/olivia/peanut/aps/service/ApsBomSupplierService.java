package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsBomSupplier.*;
import com.olivia.peanut.aps.model.ApsBomSupplier;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * 供应商表(ApsBomSupplier)表服务接口
 *
 * @author makejava
 * @since 2024-12-15 14:39:46
 */
public interface ApsBomSupplierService extends MPJBaseService<ApsBomSupplier> {
  ApsBomSupplierQueryListRes queryList(ApsBomSupplierQueryListReq req);

  DynamicsPage<ApsBomSupplierExportQueryPageListInfoRes> queryPageList(ApsBomSupplierExportQueryPageListReq req);


  void setName(List<? extends ApsBomSupplierDto> apsBomSupplierDtoList);
}

