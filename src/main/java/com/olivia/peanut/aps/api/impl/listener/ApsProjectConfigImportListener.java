package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.olivia.peanut.aps.api.entity.apsProjectConfig.ApsProjectConfigImportReq;
import com.olivia.sdk.listener.AbstractImportListener;
import com.olivia.sdk.utils.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * 项目配置表(ApsProjectConfig)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:30
 */
@Slf4j
public class ApsProjectConfigImportListener extends AbstractImportListener<ApsProjectConfigImportReq> {

  @Override
  public void invoke(ApsProjectConfigImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsProjectConfigImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
