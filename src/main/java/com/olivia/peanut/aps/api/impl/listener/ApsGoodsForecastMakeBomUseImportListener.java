package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastMakeBomUse.ApsGoodsForecastMakeBomUseImportReq;

import java.util.Map;

/**
 * (ApsGoodsForecastMakeBomUse)文件导入监听
 *
 * @author peanut
 * @since 2024-05-15 10:26:04
 */
public class ApsGoodsForecastMakeBomUseImportListener extends AnalysisEventListener<ApsGoodsForecastMakeBomUseImportReq> {

  @Override
  public void invoke(ApsGoodsForecastMakeBomUseImportReq data, AnalysisContext analysisContext) {
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
