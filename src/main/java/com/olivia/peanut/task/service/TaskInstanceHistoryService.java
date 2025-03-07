package com.olivia.peanut.task.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.task.api.entity.taskInstanceHistory.*;
import com.olivia.peanut.task.model.TaskInstanceHistory;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * 任务实例历史(TaskInstanceHistory)表服务接口
 *
 * @author makejava
 * @since 2025-03-06 13:27:08
 */
public interface TaskInstanceHistoryService extends MPJBaseService<TaskInstanceHistory> {
  TaskInstanceHistoryQueryListRes queryList(TaskInstanceHistoryQueryListReq req);

  DynamicsPage<TaskInstanceHistoryExportQueryPageListInfoRes> queryPageList(TaskInstanceHistoryExportQueryPageListReq req);


  void setName(List<? extends TaskInstanceHistoryDto> taskInstanceHistoryDtoList);
}

