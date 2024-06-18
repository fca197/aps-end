package com.olivia.peanut.portal.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.olivia.peanut.portal.api.entity.goods.JcxGoodsImportReq;
import java.util.Map;

/**
 * 商品信息(Goods)文件导入监听
 *
 * @author makejava
 * @since 2024-03-11 10:44:06
 */
public class GoodsImportListener extends AnalysisEventListener<JcxGoodsImportReq> {

  @Override
  public void invoke(JcxGoodsImportReq data, AnalysisContext analysisContext) {
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
