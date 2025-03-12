package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.olivia.peanut.aps.api.entity.apsMakeCapacityFactory.ApsMakeCapacityFactoryImportReq;
import com.olivia.sdk.listener.AbstractImportListener;
import com.olivia.sdk.utils.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * 工厂产能(ApsMakeCapacityFactory)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:28
 */
@Slf4j
public class ApsMakeCapacityFactoryImportListener extends AbstractImportListener<ApsMakeCapacityFactoryImportReq> {

  @Override
  public void invoke(ApsMakeCapacityFactoryImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsMakeCapacityFactoryImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
