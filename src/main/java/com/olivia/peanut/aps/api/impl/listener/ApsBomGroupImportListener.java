package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.fastjson2.JSON;
import com.olivia.peanut.aps.api.entity.apsBomGroup.ApsBomGroupImportReq;
import com.olivia.peanut.listener.AbstractImportListener;
import lombok.extern.slf4j.Slf4j;

/**
 * 零件组配置(ApsBomGroup)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:26
 */
@Slf4j
public class ApsBomGroupImportListener extends AbstractImportListener<ApsBomGroupImportReq> {

  @Override
  public void invoke(ApsBomGroupImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsBomGroupImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
