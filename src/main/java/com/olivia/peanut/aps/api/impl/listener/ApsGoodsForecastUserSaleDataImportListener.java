package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastUserSaleData.ApsGoodsForecastUserSaleDataImportReq;
import com.olivia.sdk.listener.AbstractImportListener;
import com.olivia.sdk.utils.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * 预测数据(ApsGoodsForecastUserSaleData)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:28
 */
@Slf4j
public class ApsGoodsForecastUserSaleDataImportListener extends AbstractImportListener<ApsGoodsForecastUserSaleDataImportReq> {

  @Override
  public void invoke(ApsGoodsForecastUserSaleDataImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsGoodsForecastUserSaleDataImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
