package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.fastjson2.JSON;
import com.olivia.peanut.aps.api.entity.apsMakeCapacitySaleConfig.ApsMakeCapacitySaleConfigImportReq;
import com.olivia.peanut.listener.AbstractImportListener;
import lombok.extern.slf4j.Slf4j;

/**
 * 工厂产能销售配置(ApsMakeCapacitySaleConfig)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:29
 */
@Slf4j
public class ApsMakeCapacitySaleConfigImportListener extends AbstractImportListener<ApsMakeCapacitySaleConfigImportReq> {

  @Override
  public void invoke(ApsMakeCapacitySaleConfigImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsMakeCapacitySaleConfigImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
