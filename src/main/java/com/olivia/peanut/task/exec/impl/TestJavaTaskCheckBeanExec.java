package com.olivia.peanut.task.exec.impl;

import com.google.common.collect.Lists;
import com.olivia.peanut.task.engine.entity.TaskCheckRunnerReq;
import com.olivia.peanut.task.engine.exec.JavaTaskCheckBeanExec;
import com.olivia.sdk.model.KVEntity;
import com.olivia.sdk.utils.Str;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("testJavaTaskCheckBeanExec")
public class TestJavaTaskCheckBeanExec implements JavaTaskCheckBeanExec {
  @Override
  public KVEntity getJavaTaskCheckBeanExecName() {
    return KVEntity.of(Str.DEFAULT_ZN, Str.DEFAULT).setChildrenList(Lists.newArrayList(KVEntity.of("TEST", "testJavaTaskCheckBeanExec")));
  }

  @Override
  public boolean exec(TaskCheckRunnerReq req) {
    log.info("return true {}", req.toString());
    return true;
  }
}
