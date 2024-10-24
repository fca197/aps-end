package com.olivia.peanut.portal.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.portal.api.entity.jcxBuyOrder.*;
import com.olivia.peanut.portal.model.JcxBuyOrder;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * (JcxBuyOrder)表服务接口
 *
 * @author peanut
 * @since 2024-03-27 13:51:37
 */
public interface JcxBuyOrderService extends MPJBaseService<JcxBuyOrder> {

  JcxBuyOrderQueryListRes queryList(JcxBuyOrderQueryListReq req);

  DynamicsPage<JcxBuyOrderExportQueryPageListInfoRes> queryPageList(JcxBuyOrderExportQueryPageListReq req);


  void setName(List<? extends JcxBuyOrderDto> jcxBuyOrderDtoList);

  JcxBuyOrderInsertRes save(JcxBuyOrderInsertReq req);

  void updateStatus(JcxBuyOrderUpdateStatusReq req);
}

