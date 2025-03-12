package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastUserGoodsData.ApsGoodsForecastUserGoodsDataImportReq;
import com.olivia.sdk.listener.AbstractImportListener;
import com.olivia.sdk.utils.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * (ApsGoodsForecastUserGoodsData)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:28
 */
@Slf4j
public class ApsGoodsForecastUserGoodsDataImportListener extends AbstractImportListener<ApsGoodsForecastUserGoodsDataImportReq> {

  @Override
  public void invoke(ApsGoodsForecastUserGoodsDataImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsGoodsForecastUserGoodsDataImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
