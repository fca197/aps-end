package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.olivia.peanut.aps.api.entity.apsSaleConfig.ApsSaleConfigImportReq;
import com.olivia.sdk.listener.AbstractImportListener;
import com.olivia.sdk.utils.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * 销售配置表(ApsSaleConfig)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:30
 */
@Slf4j
public class ApsSaleConfigImportListener extends AbstractImportListener<ApsSaleConfigImportReq> {

  @Override
  public void invoke(ApsSaleConfigImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsSaleConfigImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
