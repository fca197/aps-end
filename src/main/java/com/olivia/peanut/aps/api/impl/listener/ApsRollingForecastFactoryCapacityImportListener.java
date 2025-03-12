package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.olivia.peanut.aps.api.entity.apsRollingForecastFactoryCapacity.ApsRollingForecastFactoryCapacityImportReq;
import com.olivia.sdk.listener.AbstractImportListener;
import com.olivia.sdk.utils.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * 滚动预测(ApsRollingForecastFactoryCapacity)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:30
 */
@Slf4j
public class ApsRollingForecastFactoryCapacityImportListener extends AbstractImportListener<ApsRollingForecastFactoryCapacityImportReq> {

  @Override
  public void invoke(ApsRollingForecastFactoryCapacityImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsRollingForecastFactoryCapacityImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
