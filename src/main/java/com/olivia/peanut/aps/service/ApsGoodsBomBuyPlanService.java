package com.olivia.peanut.aps.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.aps.api.entity.apsGoodsBomBuyPlan.*;
import com.olivia.peanut.aps.model.ApsGoodsBomBuyPlan;
import com.olivia.sdk.utils.DynamicsPage;
import java.util.List;

/**
 * BOM 购买计划(ApsGoodsBomBuyPlan)表服务接口
 *
 * @author peanut
 * @since 2024-06-05 14:35:30
 */
public interface ApsGoodsBomBuyPlanService extends MPJBaseService<ApsGoodsBomBuyPlan> {

  ApsGoodsBomBuyPlanQueryListRes queryList(ApsGoodsBomBuyPlanQueryListReq req);

  DynamicsPage<ApsGoodsBomBuyPlanExportQueryPageListInfoRes> queryPageList(ApsGoodsBomBuyPlanExportQueryPageListReq req);


  void setName(List<? extends ApsGoodsBomBuyPlanDto> apsGoodsBomBuyPlanDtoList);
}

