package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.fastjson2.JSON;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastMainGoodsData.ApsGoodsForecastMainGoodsDataImportReq;
import com.olivia.peanut.listener.AbstractImportListener;
import lombok.extern.slf4j.Slf4j;

/**
 * 预测商品数据(ApsGoodsForecastMainGoodsData)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:27
 */
@Slf4j
public class ApsGoodsForecastMainGoodsDataImportListener extends AbstractImportListener<ApsGoodsForecastMainGoodsDataImportReq> {

  @Override
  public void invoke(ApsGoodsForecastMainGoodsDataImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsGoodsForecastMainGoodsDataImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
