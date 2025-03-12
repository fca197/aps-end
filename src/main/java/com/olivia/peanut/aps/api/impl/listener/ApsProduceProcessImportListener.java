package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.olivia.peanut.aps.api.entity.apsProduceProcess.ApsProduceProcessImportReq;
import com.olivia.sdk.listener.AbstractImportListener;
import com.olivia.sdk.utils.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * aps 生产路径(ApsProduceProcess)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:30
 */
@Slf4j
public class ApsProduceProcessImportListener extends AbstractImportListener<ApsProduceProcessImportReq> {

  @Override
  public void invoke(ApsProduceProcessImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsProduceProcessImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
