package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.fastjson2.JSON;
import com.olivia.peanut.aps.api.entity.apsBom.ApsBomImportReq;
import com.olivia.peanut.listener.AbstractImportListener;
import lombok.extern.slf4j.Slf4j;

/**
 * BOM 清单(ApsBom)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:26
 */
@Slf4j
public class ApsBomImportListener extends AbstractImportListener<ApsBomImportReq> {

  @Override
  public void invoke(ApsBomImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsBomImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
