package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsGoodsBomBuyPlanItem.*;
import com.olivia.peanut.aps.model.ApsGoodsBomBuyPlanItem;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * BOM 购买清单(ApsGoodsBomBuyPlanItem)表服务接口
 *
 * @author peanut
 * @since 2024-06-05 14:35:31
 */
public interface ApsGoodsBomBuyPlanItemService extends MPJBaseService<ApsGoodsBomBuyPlanItem> {

  ApsGoodsBomBuyPlanItemQueryListRes queryList(ApsGoodsBomBuyPlanItemQueryListReq req);

  DynamicsPage<ApsGoodsBomBuyPlanItemExportQueryPageListInfoRes> queryPageList(ApsGoodsBomBuyPlanItemExportQueryPageListReq req);


  void setName(List<? extends ApsGoodsBomBuyPlanItemDto> apsGoodsBomBuyPlanItemDtoList);

  SendMail2supplierRes sendMail2supplier(SendMail2supplierReq req);
}

