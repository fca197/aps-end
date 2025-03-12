package com.olivia.peanut.base.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.olivia.peanut.base.api.entity.baseOplog.BaseOplogImportReq;
import com.olivia.sdk.listener.AbstractImportListener;
import com.olivia.sdk.utils.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * 操作日志(BaseOplog)文件导入监听
 *
 * @author makejava
 * @since 2024-11-30 16:01:02
 */
@Slf4j
public class BaseOplogImportListener extends AbstractImportListener<BaseOplogImportReq> {

  @Override
  public void invoke(BaseOplogImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("BaseOplogImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
