package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.olivia.peanut.aps.api.entity.apsRoomConfig.ApsRoomConfigImportReq;
import com.olivia.sdk.listener.AbstractImportListener;
import com.olivia.sdk.utils.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * 房间配置表(ApsRoomConfig)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:30
 */
@Slf4j
public class ApsRoomConfigImportListener extends AbstractImportListener<ApsRoomConfigImportReq> {

  @Override
  public void invoke(ApsRoomConfigImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsRoomConfigImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
