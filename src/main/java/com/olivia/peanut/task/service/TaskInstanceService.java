package com.olivia.peanut.task.service;

import com.olivia.sdk.utils.DynamicsPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.olivia.peanut.task.model.TaskInstance;

import java.util.List;

import com.github.yulichang.base.MPJBaseService;

import com.olivia.peanut.task.api.entity.taskInstance.*;

/**
 * 任务流程实例(TaskInstance)表服务接口
 *
 * @author makejava
 * @since 2025-03-09 14:13:52
 */
public interface TaskInstanceService extends MPJBaseService<TaskInstance> {
  TaskInstanceQueryListRes queryList(TaskInstanceQueryListReq req);

  DynamicsPage<TaskInstanceExportQueryPageListInfoRes> queryPageList(TaskInstanceExportQueryPageListReq req);


  void setName(List<? extends TaskInstanceDto> taskInstanceDtoList);
}

