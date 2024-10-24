package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.olivia.peanut.aps.api.entity.apsOrderGoodsStatusDate.ApsOrderGoodsStatusDateImportReq;

import java.util.Map;

/**
 * 订单商品状态表(ApsOrderGoodsStatusDate)文件导入监听
 *
 * @author peanut
 * @since 2024-06-14 10:26:59
 */
public class ApsOrderGoodsStatusDateImportListener extends AnalysisEventListener<ApsOrderGoodsStatusDateImportReq> {

  @Override
  public void invoke(ApsOrderGoodsStatusDateImportReq data, AnalysisContext analysisContext) {
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
