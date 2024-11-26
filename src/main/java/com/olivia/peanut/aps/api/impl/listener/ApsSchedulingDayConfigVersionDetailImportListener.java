package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.fastjson2.JSON;
import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersionDetail.ApsSchedulingDayConfigVersionDetailImportReq;
import com.olivia.peanut.listener.AbstractImportListener;
import lombok.extern.slf4j.Slf4j;

/**
 * 排程版本配置明细表(ApsSchedulingDayConfigVersionDetail)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:31
 */
@Slf4j
public class ApsSchedulingDayConfigVersionDetailImportListener extends AbstractImportListener<ApsSchedulingDayConfigVersionDetailImportReq> {

  @Override
  public void invoke(ApsSchedulingDayConfigVersionDetailImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsSchedulingDayConfigVersionDetailImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
