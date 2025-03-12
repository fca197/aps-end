package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.olivia.peanut.aps.api.entity.apsGoods.ApsGoodsImportReq;
import com.olivia.sdk.listener.AbstractImportListener;
import com.olivia.sdk.utils.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * aps 商品表(ApsGoods)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:26
 */
@Slf4j
public class ApsGoodsImportListener extends AbstractImportListener<ApsGoodsImportReq> {

  @Override
  public void invoke(ApsGoodsImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsGoodsImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
