package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.fastjson2.JSON;
import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfig.ApsSchedulingDayConfigImportReq;
import com.olivia.peanut.listener.AbstractImportListener;
import lombok.extern.slf4j.Slf4j;

/**
 * 排程版本表(ApsSchedulingDayConfig)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:31
 */
@Slf4j
public class ApsSchedulingDayConfigImportListener extends AbstractImportListener<ApsSchedulingDayConfigImportReq> {

  @Override
  public void invoke(ApsSchedulingDayConfigImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsSchedulingDayConfigImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
