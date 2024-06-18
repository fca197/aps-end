package com.olivia.peanut.portal.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.olivia.peanut.portal.api.entity.jcxBuyOrder.JcxBuyOrderImportReq;
import java.util.Map;

/**
 * (JcxBuyOrder)文件导入监听
 *
 * @author peanut
 * @since 2024-03-27 13:51:37
 */
public class JcxBuyOrderImportListener extends AnalysisEventListener<JcxBuyOrderImportReq> {

  @Override
  public void invoke(JcxBuyOrderImportReq data, AnalysisContext analysisContext) {
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
