package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.olivia.peanut.aps.api.entity.apsOrderGoodsBom.ApsOrderGoodsBomImportReq;
import com.olivia.sdk.listener.AbstractImportListener;
import com.olivia.sdk.utils.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * 订单商品零件表(ApsOrderGoodsBom)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:29
 */
@Slf4j
public class ApsOrderGoodsBomImportListener extends AbstractImportListener<ApsOrderGoodsBomImportReq> {

  @Override
  public void invoke(ApsOrderGoodsBomImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsOrderGoodsBomImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
