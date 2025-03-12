package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.olivia.peanut.aps.api.entity.apsProcessPath.ApsProcessPathImportReq;
import com.olivia.sdk.listener.AbstractImportListener;
import com.olivia.sdk.utils.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * 流程路径表(ApsProcessPath)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:29
 */
@Slf4j
public class ApsProcessPathImportListener extends AbstractImportListener<ApsProcessPathImportReq> {

  @Override
  public void invoke(ApsProcessPathImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsProcessPathImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
