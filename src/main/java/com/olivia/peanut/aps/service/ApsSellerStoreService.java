package com.olivia.peanut.aps.service;

import com.olivia.sdk.utils.DynamicsPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.olivia.peanut.aps.model.ApsSellerStore;

import java.util.List;

import com.github.yulichang.base.MPJBaseService;

import com.olivia.peanut.aps.api.entity.apsSellerStore.*;

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

