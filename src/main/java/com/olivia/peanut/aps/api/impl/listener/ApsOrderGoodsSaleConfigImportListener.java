package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.olivia.peanut.aps.api.entity.apsOrderGoodsSaleConfig.ApsOrderGoodsSaleConfigImportReq;
import com.olivia.sdk.listener.AbstractImportListener;
import com.olivia.sdk.utils.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * 订单商品销售配置表(ApsOrderGoodsSaleConfig)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:29
 */
@Slf4j
public class ApsOrderGoodsSaleConfigImportListener extends AbstractImportListener<ApsOrderGoodsSaleConfigImportReq> {

  @Override
  public void invoke(ApsOrderGoodsSaleConfigImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsOrderGoodsSaleConfigImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
