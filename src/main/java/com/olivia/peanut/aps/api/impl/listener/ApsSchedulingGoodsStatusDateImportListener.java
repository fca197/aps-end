package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.olivia.peanut.aps.api.entity.apsSchedulingGoodsStatusDate.ApsSchedulingGoodsStatusDateImportReq;

import java.util.Map;

/**
 * 订单商品状态表(ApsSchedulingGoodsStatusDate)文件导入监听
 *
 * @author peanut
 * @since 2024-06-14 21:35:09
 */
public class ApsSchedulingGoodsStatusDateImportListener extends AnalysisEventListener<ApsSchedulingGoodsStatusDateImportReq> {

  @Override
  public void invoke(ApsSchedulingGoodsStatusDateImportReq data, AnalysisContext analysisContext) {
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
