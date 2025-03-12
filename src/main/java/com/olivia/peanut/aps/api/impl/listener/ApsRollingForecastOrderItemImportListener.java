package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.olivia.peanut.aps.api.entity.apsRollingForecastOrderItem.ApsRollingForecastOrderItemImportReq;
import com.olivia.sdk.listener.AbstractImportListener;
import com.olivia.sdk.utils.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * 滚动预测订单节点表(ApsRollingForecastOrderItem)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:30
 */
@Slf4j
public class ApsRollingForecastOrderItemImportListener extends AbstractImportListener<ApsRollingForecastOrderItemImportReq> {

  @Override
  public void invoke(ApsRollingForecastOrderItemImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsRollingForecastOrderItemImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
