package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.olivia.peanut.aps.api.entity.apsOrderGoods.ApsOrderGoodsImportReq;
import com.olivia.sdk.listener.AbstractImportListener;
import com.olivia.sdk.utils.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * 订单商品表(ApsOrderGoods)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:29
 */
@Slf4j
public class ApsOrderGoodsImportListener extends AbstractImportListener<ApsOrderGoodsImportReq> {

  @Override
  public void invoke(ApsOrderGoodsImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsOrderGoodsImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
