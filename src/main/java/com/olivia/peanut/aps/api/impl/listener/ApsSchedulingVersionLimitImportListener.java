package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.olivia.peanut.aps.api.entity.apsSchedulingVersionLimit.ApsSchedulingVersionLimitImportReq;
import com.olivia.sdk.listener.AbstractImportListener;
import com.olivia.sdk.utils.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * 排产版本限制表(ApsSchedulingVersionLimit)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:32
 */
@Slf4j
public class ApsSchedulingVersionLimitImportListener extends AbstractImportListener<ApsSchedulingVersionLimitImportReq> {

  @Override
  public void invoke(ApsSchedulingVersionLimitImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsSchedulingVersionLimitImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
