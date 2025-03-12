package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.olivia.peanut.aps.api.entity.apsGoodsBomBuyPlanItem.ApsGoodsBomBuyPlanItemImportReq;
import com.olivia.sdk.listener.AbstractImportListener;
import com.olivia.sdk.utils.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * BOM 购买清单(ApsGoodsBomBuyPlanItem)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:27
 */
@Slf4j
public class ApsGoodsBomBuyPlanItemImportListener extends AbstractImportListener<ApsGoodsBomBuyPlanItemImportReq> {

  @Override
  public void invoke(ApsGoodsBomBuyPlanItemImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsGoodsBomBuyPlanItemImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
