package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.olivia.peanut.aps.api.entity.apsProduceProcessItem.ApsProduceProcessItemImportReq;
import com.olivia.sdk.listener.AbstractImportListener;
import com.olivia.sdk.utils.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * aps 生产机器(ApsProduceProcessItem)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:30
 */
@Slf4j
public class ApsProduceProcessItemImportListener extends AbstractImportListener<ApsProduceProcessItemImportReq> {

  @Override
  public void invoke(ApsProduceProcessItemImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsProduceProcessItemImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
