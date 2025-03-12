package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastMainMakeSaleData.ApsGoodsForecastMainMakeSaleDataImportReq;
import com.olivia.sdk.listener.AbstractImportListener;
import com.olivia.sdk.utils.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * 商品预测主表-销售数据(ApsGoodsForecastMainMakeSaleData)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:27
 */
@Slf4j
public class ApsGoodsForecastMainMakeSaleDataImportListener extends AbstractImportListener<ApsGoodsForecastMainMakeSaleDataImportReq> {

  @Override
  public void invoke(ApsGoodsForecastMainMakeSaleDataImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsGoodsForecastMainMakeSaleDataImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
