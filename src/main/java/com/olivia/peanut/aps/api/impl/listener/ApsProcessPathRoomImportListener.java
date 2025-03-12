package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.olivia.peanut.aps.api.entity.apsProcessPathRoom.ApsProcessPathRoomImportReq;
import com.olivia.sdk.listener.AbstractImportListener;
import com.olivia.sdk.utils.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * 流程路径房间表(ApsProcessPathRoom)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:30
 */
@Slf4j
public class ApsProcessPathRoomImportListener extends AbstractImportListener<ApsProcessPathRoomImportReq> {

  @Override
  public void invoke(ApsProcessPathRoomImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsProcessPathRoomImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
