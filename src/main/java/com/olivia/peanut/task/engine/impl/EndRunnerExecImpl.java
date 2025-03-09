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
@Component("end" + "RunnerExecImpl")
public class EndRunnerExecImpl implements TaskRunnerExec {

  @Override
  public KVEntity getTaskRunnerExecName() {
    return KVEntity.of(Str.DEFAULT_ZN, Str.DEFAULT).setChildrenList(Lists.newArrayList(KVEntity.of("结束", "END")));
  }

  @Override
  public Map<String, Object> exec(ExecTaskReq req) {
    log.info("instanceId : {} lastTaskInstanceId : {} taskInstanceId: {} is end", req.getInstanceId(), req.getLastTaskInstanceId(), req.getTaskInstanceId());
    return req.getLastOutMap();
  }
}
