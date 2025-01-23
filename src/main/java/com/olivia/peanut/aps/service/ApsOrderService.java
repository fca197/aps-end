package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsOrder.*;
import com.olivia.peanut.aps.model.ApsOrder;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * (ApsOrder)表服务接口
 *
 * @author peanut
 * @since 2024-04-09 13:19:35
 */
public interface ApsOrderService extends MPJBaseService<ApsOrder> {

  ApsOrderQueryListRes queryList(ApsOrderQueryListReq req);

  DynamicsPage<ApsOrderExportQueryPageListInfoRes> queryPageList(ApsOrderExportQueryPageListReq req);


  void setName(List<? extends ApsOrderDto> apsOrderDtoList);

  ApsOrderInsertRes save(ApsOrderInsertReq req);

  ApsOrder getApsOrderByNo(String orderNo);

  ApsOrderBatchInsertRes saveBatch(ApsOrderBatchInsertReq req);

  DynamicsPage<ApsOrderTimeLineRes> timeLine(ApsOrderTimeLineReq req);

  ApsOrderUpdateOrderStatusRes updateOrderStatus(ApsOrderUpdateOrderStatusReq req);

  ApsOrderUpdateSchedulingDateRes updateSchedulingDate(ApsOrderUpdateSchedulingDateReq req);

  OrderCreateDayCountRes orderCreateDayCount(OrderCreateDayCountReq req);
}

