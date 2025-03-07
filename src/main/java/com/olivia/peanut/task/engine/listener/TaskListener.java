package com.olivia.peanut.task.engine.listener;

import com.olivia.peanut.task.engine.entity.ExecTaskReq;
import com.olivia.sdk.model.KVEntity;

import java.util.List;

public interface TaskListener {

  // 监听器不用区分前置OR后置
  //  TaskListenerType getTaskListenerType();

  /****
   * 返回监听器类型
   * @return 该监听器列表
   */
  List<KVEntity> getTaskListenerName();

  /***
   * 执行监听器
   * @param req 请求
   */
  void execListener(ExecTaskReq req);
}
