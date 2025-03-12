package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.olivia.peanut.aps.api.entity.apsGoodsSaleItem.ApsGoodsSaleItemImportReq;
import com.olivia.sdk.listener.AbstractImportListener;
import com.olivia.sdk.utils.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * 商品销售配置(ApsGoodsSaleItem)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:28
 */
@Slf4j
public class ApsGoodsSaleItemImportListener extends AbstractImportListener<ApsGoodsSaleItemImportReq> {

  @Override
  public void invoke(ApsGoodsSaleItemImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsGoodsSaleItemImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
