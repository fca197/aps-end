package com.olivia.peanut.task.engine.exec;


import com.olivia.peanut.task.engine.entity.ExecTaskReq;

import java.util.Map;

public interface TaskRunnerExec {
  Map<String, Object> exec(ExecTaskReq req);
}
