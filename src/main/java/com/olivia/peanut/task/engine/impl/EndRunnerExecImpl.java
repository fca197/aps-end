package com.olivia.peanut.task.engine.impl;

import com.google.common.collect.Lists;
import com.olivia.peanut.task.engine.entity.ExecTaskReq;
import com.olivia.peanut.task.engine.exec.TaskRunnerExec;
import com.olivia.sdk.model.KVEntity;
import com.olivia.sdk.utils.Str;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component("endRunnerExecImpl")
public class EndRunnerExecImpl implements TaskRunnerExec {

  @Override
  public KVEntity getTaskRunnerExecName() {
    return KVEntity.of(Str.DEFAULT_ZN, Str.DEFAULT).setChildrenList(Lists.newArrayList(KVEntity.of("END", "endRunnerExecImpl")));
  }

  @Override
  public Map<String, Object> exec(ExecTaskReq req) {
    log.info("instanceId : {} lastTaskInstanceId : {} taskInstanceId: {}", req.getInstanceId(), req.getLastTaskInstanceId(), req.getTaskInstanceId());
    return req.getLastOutMap();
  }
}
