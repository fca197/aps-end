package com.olivia.peanut.task.api.impl.listener;


import com.olivia.peanut.task.model.TaskInstanceHistory;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.olivia.peanut.task.api.entity.taskInstanceHistory.*;
import com.alibaba.excel.context.AnalysisContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import com.olivia.sdk.listener.AbstractImportListener;

import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson2.JSON;

/**
 * 任务实例历史(TaskInstanceHistory)文件导入监听
 *
 * @author makejava
 * @since 2025-03-06 13:27:08
 */
@Slf4j
public class TaskInstanceHistoryImportListener extends AbstractImportListener<TaskInstanceHistoryImportReq> {

  @Override
  public void invoke(TaskInstanceHistoryImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("TaskInstanceHistoryImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
