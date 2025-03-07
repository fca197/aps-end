package com.olivia.peanut.task.api.impl.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.fastjson2.JSON;
import com.olivia.peanut.task.api.entity.taskInstanceHistory.TaskInstanceHistoryImportReq;
import com.olivia.sdk.listener.AbstractImportListener;
import lombok.extern.slf4j.Slf4j;

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
