package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.olivia.peanut.aps.api.entity.apsLogisticsPathItem.ApsLogisticsPathItemImportReq;
import com.olivia.sdk.listener.AbstractImportListener;
import com.olivia.sdk.utils.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * 物流路详情径表(ApsLogisticsPathItem)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:28
 */
@Slf4j
public class ApsLogisticsPathItemImportListener extends AbstractImportListener<ApsLogisticsPathItemImportReq> {

  @Override
  public void invoke(ApsLogisticsPathItemImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsLogisticsPathItemImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
