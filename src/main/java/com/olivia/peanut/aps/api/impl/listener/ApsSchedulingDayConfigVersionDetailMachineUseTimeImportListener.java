package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersionDetailMachineUseTime.ApsSchedulingDayConfigVersionDetailMachineUseTimeImportReq;
import com.olivia.sdk.listener.AbstractImportListener;
import com.olivia.sdk.utils.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * 排程结果机器使用率(ApsSchedulingDayConfigVersionDetailMachineUseTime)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:31
 */
@Slf4j
public class ApsSchedulingDayConfigVersionDetailMachineUseTimeImportListener extends AbstractImportListener<ApsSchedulingDayConfigVersionDetailMachineUseTimeImportReq> {

  @Override
  public void invoke(ApsSchedulingDayConfigVersionDetailMachineUseTimeImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsSchedulingDayConfigVersionDetailMachineUseTimeImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
