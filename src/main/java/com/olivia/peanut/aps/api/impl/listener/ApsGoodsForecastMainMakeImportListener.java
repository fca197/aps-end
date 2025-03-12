package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastMainMake.ApsGoodsForecastMainMakeImportReq;
import com.olivia.sdk.listener.AbstractImportListener;
import com.olivia.sdk.utils.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * 预测生产主表(ApsGoodsForecastMainMake)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:27
 */
@Slf4j
public class ApsGoodsForecastMainMakeImportListener extends AbstractImportListener<ApsGoodsForecastMainMakeImportReq> {

  @Override
  public void invoke(ApsGoodsForecastMainMakeImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsGoodsForecastMainMakeImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
