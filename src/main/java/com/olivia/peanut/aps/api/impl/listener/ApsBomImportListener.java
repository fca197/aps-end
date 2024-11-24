package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson2.JSON;
import com.olivia.peanut.aps.api.entity.apsBom.ApsBomImportReq;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * BOM 清单(ApsBom)文件导入监听
 *
 * @author peanut
 * @since 2024-06-06 11:27:34
 */
@Slf4j
public class ApsBomImportListener extends AnalysisEventListener<ApsBomImportReq> {

  @Override
  public void invoke(ApsBomImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsBomImportListener invoke data:{}", JSON.toJSONString(data));
  }

  @Override
  public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    // 数据处理完毕后的操作（如果需要）
  }

  @Override
  public void onException(Exception exception, AnalysisContext context) throws Exception {
    // 异常处理
    super.onException(exception, context);
  }

  @Override
  public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
    //  log.info("headMap:{}", JSON.toJSONString(headMap));
    super.invokeHeadMap(headMap, context);
  }

}
