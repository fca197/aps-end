package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.olivia.peanut.aps.api.entity.apsBomSupplier.ApsBomSupplierImportReq;
import com.olivia.sdk.listener.AbstractImportListener;
import com.olivia.sdk.utils.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * 供应商表(ApsBomSupplier)文件导入监听
 *
 * @author makejava
 * @since 2024-12-15 14:39:46
 */
@Slf4j
public class ApsBomSupplierImportListener extends AbstractImportListener<ApsBomSupplierImportReq> {

  @Override
  public void invoke(ApsBomSupplierImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsBomSupplierImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
