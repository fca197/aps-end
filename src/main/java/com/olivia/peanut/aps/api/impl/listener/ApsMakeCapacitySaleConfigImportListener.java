package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.olivia.peanut.aps.api.entity.apsMakeCapacitySaleConfig.ApsMakeCapacitySaleConfigImportReq;
import java.util.Map;

/**
 * (ApsMakeCapacitySaleConfig)文件导入监听
 *
 * @author peanut
 * @since 2024-04-13 12:05:07
 */
public class ApsMakeCapacitySaleConfigImportListener extends AnalysisEventListener<ApsMakeCapacitySaleConfigImportReq> {

  @Override
  public void invoke(ApsMakeCapacitySaleConfigImportReq data, AnalysisContext analysisContext) {
    //  文件校验
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
