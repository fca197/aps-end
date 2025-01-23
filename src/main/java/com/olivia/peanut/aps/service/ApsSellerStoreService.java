package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsSellerStore.*;
import com.olivia.peanut.aps.model.ApsSellerStore;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * aps销售门店(ApsSellerStore)表服务接口
 *
 * @author makejava
 * @since 2024-11-15 14:58:59
 */
public interface ApsSellerStoreService extends MPJBaseService<ApsSellerStore> {
  ApsSellerStoreQueryListRes queryList(ApsSellerStoreQueryListReq req);

  DynamicsPage<ApsSellerStoreExportQueryPageListInfoRes> queryPageList(ApsSellerStoreExportQueryPageListReq req);


  void setName(List<? extends ApsSellerStoreDto> apsSellerStoreDtoList);
}

