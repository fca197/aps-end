package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.olivia.peanut.aps.api.entity.apsSchedulingVersionDay.ApsSchedulingVersionDayImportReq;
import com.olivia.sdk.listener.AbstractImportListener;
import com.olivia.sdk.utils.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * 排产版本天表(ApsSchedulingVersionDay)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:32
 */
@Slf4j
public class ApsSchedulingVersionDayImportListener extends AbstractImportListener<ApsSchedulingVersionDayImportReq> {

  @Override
  public void invoke(ApsSchedulingVersionDayImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsSchedulingVersionDayImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
