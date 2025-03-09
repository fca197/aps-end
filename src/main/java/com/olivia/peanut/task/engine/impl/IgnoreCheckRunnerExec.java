package com.olivia.peanut.task.engine.impl;

import com.google.common.collect.Lists;
import com.olivia.peanut.task.engine.entity.TaskCheckRunnerReq;
import com.olivia.peanut.task.engine.exec.TaskCheckRunnerExec;
import com.olivia.sdk.model.KVEntity;
import com.olivia.sdk.utils.Str;
import org.springframework.stereotype.Component;

@Component("ignoreTaskCheckRunnerExec")
public class IgnoreCheckRunnerExec implements TaskCheckRunnerExec {
  @Override
  public KVEntity getTaskCheckRunnerName() {
    return KVEntity.of(Str.DEFAULT_ZN, Str.DEFAULT).setChildrenList(Lists.newArrayList(KVEntity.of("忽略检查", "IGNORE")));
  }

  @Override
  public void exec(TaskCheckRunnerReq req) {
    log.info("忽略检查");
  }
}
