package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.olivia.peanut.aps.api.entity.apsStatus.ApsStatusImportReq;
import com.olivia.sdk.listener.AbstractImportListener;
import com.olivia.sdk.utils.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * 排产状态表(ApsStatus)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:32
 */
@Slf4j
public class ApsStatusImportListener extends AbstractImportListener<ApsStatusImportReq> {

  @Override
  public void invoke(ApsStatusImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsStatusImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
