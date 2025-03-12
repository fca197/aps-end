package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.olivia.peanut.aps.api.entity.apsRoom.ApsRoomImportReq;
import com.olivia.sdk.listener.AbstractImportListener;
import com.olivia.sdk.utils.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * 房间配置表(ApsRoom)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:30
 */
@Slf4j
public class ApsRoomImportListener extends AbstractImportListener<ApsRoomImportReq> {

  @Override
  public void invoke(ApsRoomImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsRoomImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
