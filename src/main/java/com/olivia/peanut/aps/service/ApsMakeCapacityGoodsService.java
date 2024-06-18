package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsMakeCapacityGoods.*;
import com.olivia.peanut.aps.model.ApsMakeCapacityGoods;
import com.olivia.sdk.utils.DynamicsPage;
import java.util.List;

/**
 * (ApsMakeCapacityGoods)表服务接口
 *
 * @author peanut
 * @since 2024-04-13 12:05:05
 */
public interface ApsMakeCapacityGoodsService extends MPJBaseService<ApsMakeCapacityGoods> {

  ApsMakeCapacityGoodsQueryListRes queryList(ApsMakeCapacityGoodsQueryListReq req);

  DynamicsPage<ApsMakeCapacityGoodsExportQueryPageListInfoRes> queryPageList(ApsMakeCapacityGoodsExportQueryPageListReq req);


  void setName(List<? extends ApsMakeCapacityGoodsDto> apsMakeCapacityGoodsDtoList);

  ApsMakeCapacityGoodsInsertRes save(ApsMakeCapacityGoodsInsertReq req);
}

