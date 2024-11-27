package com.olivia.peanut.flow.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.olivia.peanut.flow.api.entity.flowForm.FlowFormImportReq;

import java.util.Map;

/**
 * 工作流表单表(FlowForm)文件导入监听
 *
 * @author peanut
 * @since 2024-08-02 23:26:22
 */
public class FlowFormImportListener extends AnalysisEventListener<FlowFormImportReq> {

  @Override
  public void invoke(FlowFormImportReq data, AnalysisContext analysisContext) {
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
