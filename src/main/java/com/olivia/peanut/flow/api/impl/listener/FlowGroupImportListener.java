package com.olivia.peanut.flow.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.olivia.peanut.flow.api.entity.flowGroup.FlowGroupImportReq;

import java.util.Map;

/**
 * 工作流组表(FlowGroup)文件导入监听
 *
 * @author peanut
 * @since 2024-08-01 10:43:53
 */
public class FlowGroupImportListener extends AnalysisEventListener<FlowGroupImportReq> {

  @Override
  public void invoke(FlowGroupImportReq data, AnalysisContext analysisContext) {
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
