package com.olivia.peanut.task.exec.impl;

import com.olivia.peanut.task.engine.entity.TaskCheckRunnerReq;
import com.olivia.peanut.task.engine.exec.JavaTaskCheckBeanExec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("testJavaTaskCheckBeanExec")
public class TestJavaTaskCheckBeanExec implements JavaTaskCheckBeanExec {
  @Override
  public boolean exec(TaskCheckRunnerReq req) {
    log.info("return true {}", req.toString());
    return true;
  }
}
