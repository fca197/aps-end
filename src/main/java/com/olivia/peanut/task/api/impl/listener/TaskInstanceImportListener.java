package com.olivia.peanut.task.api.impl.listener;


import com.olivia.peanut.task.model.TaskInstance;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.olivia.peanut.task.api.entity.taskInstance.*;
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
 * 任务流程实例(TaskInstance)文件导入监听
 *
 * @author makejava
 * @since 2025-03-09 14:13:52
 */
@Slf4j
public class TaskInstanceImportListener extends AbstractImportListener<TaskInstanceImportReq> {

  @Override
  public void invoke(TaskInstanceImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("TaskInstanceImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
