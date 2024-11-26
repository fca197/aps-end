package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.fastjson2.JSON;
import com.olivia.peanut.aps.api.entity.apsSellerStore.ApsSellerStoreImportReq;
import com.olivia.peanut.listener.AbstractImportListener;
import lombok.extern.slf4j.Slf4j;

/**
 * aps销售门店(ApsSellerStore)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:32
 */
@Slf4j
public class ApsSellerStoreImportListener extends AbstractImportListener<ApsSellerStoreImportReq> {

  @Override
  public void invoke(ApsSellerStoreImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsSellerStoreImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
