package com.olivia.peanut.aps.api.impl.listener;


import com.olivia.peanut.aps.model.ApsSchedulingDayConfigVersionDetailMachine;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersionDetailMachine.*;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * 排程版本详情_机器(ApsSchedulingDayConfigVersionDetailMachine)文件导入监听
 *
 * @author makejava
 * @since 2024-10-27 00:12:55
 */
public class ApsSchedulingDayConfigVersionDetailMachineImportListener extends AnalysisEventListener<ApsSchedulingDayConfigVersionDetailMachineImportReq> {

  @Override
  public void invoke(ApsSchedulingDayConfigVersionDetailMachineImportReq data, AnalysisContext analysisContext) {
    //  文件校验
  }

  @Override
  public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    // 数据处理完毕后的操作（如果需要）
  }

  @Override
  public void onException(Exception exception, AnalysisContext context) throws Exception {
    // 异常处理
    super.onException(exception, context);
  }

  @Override
  public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
    //  log.info("headMap:{}", JSON.toJSONString(headMap));
    super.invokeHeadMap(headMap, context);
  }


}
