package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsMakeCapacityFactory.*;
import com.olivia.peanut.aps.model.ApsMakeCapacityFactory;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * (ApsMakeCapacityFactory)表服务接口
 *
 * @author peanut
 * @since 2024-04-13 12:05:04
 */
public interface ApsMakeCapacityFactoryService extends MPJBaseService<ApsMakeCapacityFactory> {

  ApsMakeCapacityFactoryQueryListRes queryList(ApsMakeCapacityFactoryQueryListReq req);

  DynamicsPage<ApsMakeCapacityFactoryExportQueryPageListInfoRes> queryPageList(ApsMakeCapacityFactoryExportQueryPageListReq req);


  void setName(List<? extends ApsMakeCapacityFactoryDto> apsMakeCapacityFactoryDtoList);

  ApsMakeCapacityFactoryInsertRes save(ApsMakeCapacityFactoryInsertReq req);
}

