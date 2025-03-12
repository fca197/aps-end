package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.olivia.peanut.aps.api.entity.apsLogisticsPath.ApsLogisticsPathImportReq;
import com.olivia.sdk.listener.AbstractImportListener;
import com.olivia.sdk.utils.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * 物流路径表(ApsLogisticsPath)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:28
 */
@Slf4j
public class ApsLogisticsPathImportListener extends AbstractImportListener<ApsLogisticsPathImportReq> {

  @Override
  public void invoke(ApsLogisticsPathImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsLogisticsPathImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
