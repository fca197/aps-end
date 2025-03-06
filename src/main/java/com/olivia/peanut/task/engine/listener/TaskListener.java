package com.olivia.peanut.task.engine.listener;

import com.olivia.peanut.task.engine.entity.ExecTaskReq;
import com.olivia.peanut.task.engine.entity.vo.TaskListenerType;
import com.olivia.sdk.model.KVEntity;

import java.util.List;

public interface TaskListener {

  TaskListenerType getTaskListenerType();

  List<KVEntity> getTaskListenerName();

  void execListener(ExecTaskReq req);
}
