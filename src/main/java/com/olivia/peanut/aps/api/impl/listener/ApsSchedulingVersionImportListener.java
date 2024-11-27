package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.fastjson2.JSON;
import com.olivia.peanut.aps.api.entity.apsSchedulingVersion.ApsSchedulingVersionImportReq;
import com.olivia.sdk.listener.AbstractImportListener;
import lombok.extern.slf4j.Slf4j;

/**
 * 排产版本表(ApsSchedulingVersion)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:31
 */
@Slf4j
public class ApsSchedulingVersionImportListener extends AbstractImportListener<ApsSchedulingVersionImportReq> {

  @Override
  public void invoke(ApsSchedulingVersionImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsSchedulingVersionImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
