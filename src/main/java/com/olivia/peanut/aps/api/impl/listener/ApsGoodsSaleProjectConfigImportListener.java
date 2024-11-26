package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.fastjson2.JSON;
import com.olivia.peanut.aps.api.entity.apsGoodsSaleProjectConfig.ApsGoodsSaleProjectConfigImportReq;
import com.olivia.peanut.listener.AbstractImportListener;
import lombok.extern.slf4j.Slf4j;

/**
 * 项目配置(ApsGoodsSaleProjectConfig)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:28
 */
@Slf4j
public class ApsGoodsSaleProjectConfigImportListener extends AbstractImportListener<ApsGoodsSaleProjectConfigImportReq> {

  @Override
  public void invoke(ApsGoodsSaleProjectConfigImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsGoodsSaleProjectConfigImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
