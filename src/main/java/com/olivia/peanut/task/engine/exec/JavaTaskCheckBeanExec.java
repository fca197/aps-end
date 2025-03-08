package com.olivia.peanut.task.engine.exec;

import com.olivia.peanut.task.engine.entity.TaskCheckRunnerReq;

public interface JavaTaskCheckBeanExec {
  boolean exec(TaskCheckRunnerReq req);
}
