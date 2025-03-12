package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.olivia.peanut.aps.api.entity.apsOrderGoodsHistory.ApsOrderGoodsHistoryImportReq;
import com.olivia.sdk.listener.AbstractImportListener;
import com.olivia.sdk.utils.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * 历史订单记录(ApsOrderGoodsHistory)文件导入监听
 *
 * @author makejava
 * @since 2025-02-20 17:18:46
 */
@Slf4j
public class ApsOrderGoodsHistoryImportListener extends AbstractImportListener<ApsOrderGoodsHistoryImportReq> {

  @Override
  public void invoke(ApsOrderGoodsHistoryImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsOrderGoodsHistoryImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
