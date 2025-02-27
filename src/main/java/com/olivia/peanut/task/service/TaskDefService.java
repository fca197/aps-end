package com.olivia.peanut.task.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.task.api.entity.taskDef.*;
import com.olivia.peanut.task.model.TaskDef;
import com.olivia.sdk.utils.DynamicsPage;

import java.util.List;

/**
 * 任务定义(TaskDef)表服务接口
 *
 * @author makejava
 * @since 2025-02-26 16:17:46
 */
public interface TaskDefService extends MPJBaseService<TaskDef> {
  TaskDefQueryListRes queryList(TaskDefQueryListReq req);

  DynamicsPage<TaskDefExportQueryPageListInfoRes> queryPageList(TaskDefExportQueryPageListReq req);


  void setName(List<? extends TaskDefDto> taskDefDtoList);
}

