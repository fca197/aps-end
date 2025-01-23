package com.olivia.peanut.flow.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.olivia.peanut.flow.api.entity.flowFormUserValue.FlowFormUserValueImportReq;

import java.util.Map;

/**
 * 工作流表单用户数据表(FlowFormUserValue)文件导入监听
 *
 * @author peanut
 * @since 2024-08-03 18:10:54
 */
public class FlowFormUserValueImportListener extends AnalysisEventListener<FlowFormUserValueImportReq> {

  @Override
  public void invoke(FlowFormUserValueImportReq data, AnalysisContext analysisContext) {
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
