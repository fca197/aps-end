package com.olivia.peanut.task.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.fastjson2.JSON;
import com.olivia.peanut.task.api.entity.taskDef.TaskDefImportReq;
import com.olivia.sdk.listener.AbstractImportListener;
import lombok.extern.slf4j.Slf4j;

/**
 * 任务定义(TaskDef)文件导入监听
 *
 * @author makejava
 * @since 2025-02-26 16:17:46
 */
@Slf4j
public class TaskDefImportListener extends AbstractImportListener<TaskDefImportReq> {

  @Override
  public void invoke(TaskDefImportReq data, AnalysisContext analysisContext) {
    //  文件校验
    log.info("TaskDefImportListener invoke data:{}", JSON.toJSONString(data));
    checkData(data, analysisContext);

  }

}
