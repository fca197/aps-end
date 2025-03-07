package com.olivia.peanut.task.engine;

import com.olivia.peanut.task.engine.entity.ExecTaskReq;

import java.util.Map;

public interface TaskBeanExec {
  Map<String, Object> exec(ExecTaskReq req);
}
