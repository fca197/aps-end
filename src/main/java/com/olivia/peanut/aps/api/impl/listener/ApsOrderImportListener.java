package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.olivia.peanut.aps.api.entity.apsOrder.ApsOrderImportReq;
import com.olivia.sdk.listener.AbstractImportListener;
import com.olivia.sdk.utils.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * 订单表(ApsOrder)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:29
 */
@Slf4j
public class ApsOrderImportListener extends AbstractImportListener<ApsOrderImportReq> {

  @Override
  public void invoke(ApsOrderImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsOrderImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
