package com.olivia.peanut.base.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.olivia.peanut.base.api.entity.baseH3Code.BaseH3CodeImportReq;

import java.util.Map;

/**
 * H3对应的值(BaseH3Code)文件导入监听
 *
 * @author makejava
 * @since 2024-11-19 16:09:19
 */
public class BaseH3CodeImportListener extends AnalysisEventListener<BaseH3CodeImportReq> {

  @Override
  public void invoke(BaseH3CodeImportReq data, AnalysisContext analysisContext) {
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
