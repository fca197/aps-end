package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.olivia.peanut.aps.api.entity.apsMakeCapacityGoods.ApsMakeCapacityGoodsImportReq;
import com.olivia.sdk.listener.AbstractImportListener;
import com.olivia.sdk.utils.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * 工厂产能商品(ApsMakeCapacityGoods)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:29
 */
@Slf4j
public class ApsMakeCapacityGoodsImportListener extends AbstractImportListener<ApsMakeCapacityGoodsImportReq> {

  @Override
  public void invoke(ApsMakeCapacityGoodsImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsMakeCapacityGoodsImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
