package com.olivia.peanut.portal.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.portal.api.entity.jcxBuyOrderItem.*;
import com.olivia.peanut.portal.model.JcxBuyOrderItem;
import com.olivia.sdk.utils.DynamicsPage;
import java.util.List;

/**
 * (JcxBuyOrderItem)表服务接口
 *
 * @author peanut
 * @since 2024-03-27 13:51:37
 */
public interface JcxBuyOrderItemService extends MPJBaseService<JcxBuyOrderItem> {

  JcxBuyOrderItemQueryListRes queryList(JcxBuyOrderItemQueryListReq req);

  DynamicsPage<JcxBuyOrderItemExportQueryPageListInfoRes> queryPageList(JcxBuyOrderItemExportQueryPageListReq req);


  void setName(List<? extends JcxBuyOrderItemDto> JcxBuyOrderItemDtoList);
}

