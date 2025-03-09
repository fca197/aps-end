package com.olivia.peanut.task.engine.exec;


import com.olivia.peanut.task.engine.entity.ExecTaskReq;
import com.olivia.sdk.model.KVEntity;

import java.util.Map;

public interface TaskRunnerExec {
  KVEntity getTaskRunnerExecName();

  Map<String, Object> exec(ExecTaskReq req);
}
