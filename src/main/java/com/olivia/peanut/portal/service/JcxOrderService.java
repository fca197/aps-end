package com.olivia.peanut.portal.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.portal.api.entity.jcxOrder.*;
import com.olivia.peanut.portal.model.JcxOrder;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * (JcxOrder)表服务接口
 *
 * @author peanut
 * @since 2024-03-22 10:38:06
 */
public interface JcxOrderService extends MPJBaseService<JcxOrder> {

  JcxOrderQueryListRes queryList(JcxOrderQueryListReq req);

  DynamicsPage<JcxOrderExportQueryPageListInfoRes> queryPageList(JcxOrderExportQueryPageListReq req);

  void save(JcxOrderInsertReq req);

  void setName(Boolean queryOrderItem, List<? extends JcxOrderDto> orderList);
}

