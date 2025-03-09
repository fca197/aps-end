package com.olivia.peanut.task.engine.exec;

import com.olivia.peanut.task.engine.entity.TaskCheckRunnerReq;
import com.olivia.sdk.model.KVEntity;

public interface JavaTaskCheckBeanExec {
  KVEntity getJavaTaskCheckBeanExecName();
  boolean exec(TaskCheckRunnerReq req);
}
