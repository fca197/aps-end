package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.olivia.peanut.aps.api.entity.apsMachine.ApsMachineImportReq;
import com.olivia.sdk.listener.AbstractImportListener;
import com.olivia.sdk.utils.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * aps 生产机器(ApsMachine)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:28
 */
@Slf4j
public class ApsMachineImportListener extends AbstractImportListener<ApsMachineImportReq> {

  @Override
  public void invoke(ApsMachineImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsMachineImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
