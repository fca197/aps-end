package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.olivia.peanut.aps.api.entity.apsGoodsBomBuyPlan.ApsGoodsBomBuyPlanImportReq;
import com.olivia.sdk.listener.AbstractImportListener;
import com.olivia.sdk.utils.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * BOM 购买计划(ApsGoodsBomBuyPlan)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:27
 */
@Slf4j
public class ApsGoodsBomBuyPlanImportListener extends AbstractImportListener<ApsGoodsBomBuyPlanImportReq> {

  @Override
  public void invoke(ApsGoodsBomBuyPlanImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsGoodsBomBuyPlanImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
