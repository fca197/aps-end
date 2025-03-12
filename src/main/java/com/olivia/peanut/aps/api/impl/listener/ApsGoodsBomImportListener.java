package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.olivia.peanut.aps.api.entity.apsGoodsBom.ApsGoodsBomImportReq;
import com.olivia.sdk.listener.AbstractImportListener;
import com.olivia.sdk.utils.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * BOM 清单(ApsGoodsBom)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:27
 */
@Slf4j
public class ApsGoodsBomImportListener extends AbstractImportListener<ApsGoodsBomImportReq> {

  @Override
  public void invoke(ApsGoodsBomImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsGoodsBomImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
