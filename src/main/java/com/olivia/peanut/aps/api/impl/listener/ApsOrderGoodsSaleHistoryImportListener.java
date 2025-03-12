package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.olivia.peanut.aps.api.entity.apsOrderGoodsSaleHistory.ApsOrderGoodsSaleHistoryImportReq;
import com.olivia.sdk.listener.AbstractImportListener;
import com.olivia.sdk.utils.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * 销售规划订单历史销售占比(ApsOrderGoodsSaleHistory)文件导入监听
 *
 * @author makejava
 * @since 2025-02-18 14:28:42
 */
@Slf4j
public class ApsOrderGoodsSaleHistoryImportListener extends AbstractImportListener<ApsOrderGoodsSaleHistoryImportReq> {

  @Override
  public void invoke(ApsOrderGoodsSaleHistoryImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsOrderGoodsSaleHistoryImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
