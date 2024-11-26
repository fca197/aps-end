package com.olivia.peanut.aps.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.fastjson2.JSON;
import com.olivia.peanut.aps.api.entity.apsSchedulingVersionCapacity.ApsSchedulingVersionCapacityImportReq;
import com.olivia.peanut.listener.AbstractImportListener;
import lombok.extern.slf4j.Slf4j;

/**
 * 排产版本容量表(ApsSchedulingVersionCapacity)文件导入监听
 *
 * @author makejava
 * @since 2024-11-26 15:49:31
 */
@Slf4j
public class ApsSchedulingVersionCapacityImportListener extends AbstractImportListener<ApsSchedulingVersionCapacityImportReq> {

  @Override
  public void invoke(ApsSchedulingVersionCapacityImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("ApsSchedulingVersionCapacityImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
