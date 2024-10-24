package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsOrderGoodsStatusDate.*;
import com.olivia.peanut.aps.model.ApsOrderGoodsStatusDate;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * 订单商品状态表(ApsOrderGoodsStatusDate)表服务接口
 *
 * @author peanut
 * @since 2024-06-14 10:26:59
 */
public interface ApsOrderGoodsStatusDateService extends MPJBaseService<ApsOrderGoodsStatusDate> {

  ApsOrderGoodsStatusDateQueryListRes queryList(ApsOrderGoodsStatusDateQueryListReq req);

  DynamicsPage<ApsOrderGoodsStatusDateExportQueryPageListInfoRes> queryPageList(ApsOrderGoodsStatusDateExportQueryPageListReq req);

  List<ApsOrderGoodsStatusDate> listByOrderIdGoodsId(Long orderId, Long goodsId);

  void setName(List<? extends ApsOrderGoodsStatusDateDto> apsOrderGoodsStatusDateDtoList);
}

