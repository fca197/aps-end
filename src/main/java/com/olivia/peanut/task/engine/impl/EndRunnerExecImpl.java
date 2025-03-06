package com.olivia.peanut.task.engine.impl;

import com.olivia.peanut.task.engine.TaskRunnerExec;
import com.olivia.peanut.task.engine.entity.ExecTaskReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component("end" + "TaskRunnerExec")
public class EndRunnerExecImpl implements TaskRunnerExec {


  @Override
  public Map<String, Object> exec(ExecTaskReq req) {
    log.info("instanceId : {} lastTaskInstanceId : {} taskInstanceId: {}", req.getInstanceId(), req.getLastTaskInstanceId(), req.getTaskInstanceId());
    return req.getLastOutMap();
  }
}
