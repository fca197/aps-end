package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsOrderGoods.*;
import com.olivia.peanut.aps.model.ApsOrderGoods;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * (ApsOrderGoods)表服务接口
 *
 * @author peanut
 * @since 2024-04-09 13:19:36
 */
public interface ApsOrderGoodsService extends MPJBaseService<ApsOrderGoods> {

  ApsOrderGoodsQueryListRes queryList(ApsOrderGoodsQueryListReq req);

  DynamicsPage<ApsOrderGoodsExportQueryPageListInfoRes> queryPageList(ApsOrderGoodsExportQueryPageListReq req);


  void setName(List<? extends ApsOrderGoodsDto> apsOrderGoodsDtoList);

  List<ApsOrderGoods> getApsOrderGoodsByOrderId(Long orderId);
}

