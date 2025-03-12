package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.olivia.peanut.aps.api.entity.apsSchedulingIssueItem.ApsSchedulingIssueItemImportReq;
import com.olivia.sdk.listener.AbstractImportListener;
import com.olivia.sdk.utils.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * 排产下发详情(ApsSchedulingIssueItem)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:31
 */
@Slf4j
public class ApsSchedulingIssueItemImportListener extends AbstractImportListener<ApsSchedulingIssueItemImportReq> {

  @Override
  public void invoke(ApsSchedulingIssueItemImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsSchedulingIssueItemImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
