package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.olivia.peanut.aps.api.entity.workshopSection.WorkshopSectionImportReq;

import java.util.Map;

/**
 * 工段信息(WorkshopSection)文件导入监听
 *
 * @author makejava
 * @since 2024-03-11 10:55:23
 */
public class WorkshopSectionImportListener extends AnalysisEventListener<WorkshopSectionImportReq> {

  @Override
  public void invoke(WorkshopSectionImportReq data, AnalysisContext analysisContext) {
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
