package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastMakeBomUse.ApsGoodsForecastMakeBomUseImportReq;
import com.olivia.sdk.listener.AbstractImportListener;
import com.olivia.sdk.utils.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * 商品预测-生产BOM使用(ApsGoodsForecastMakeBomUse)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:28
 */
@Slf4j
public class ApsGoodsForecastMakeBomUseImportListener extends AbstractImportListener<ApsGoodsForecastMakeBomUseImportReq> {

  @Override
  public void invoke(ApsGoodsForecastMakeBomUseImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsGoodsForecastMakeBomUseImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
