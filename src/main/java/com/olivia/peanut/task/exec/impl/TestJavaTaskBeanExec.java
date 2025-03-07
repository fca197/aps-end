package com.olivia.peanut.task.exec.impl;

import com.olivia.peanut.task.engine.entity.ExecTaskReq;
import com.olivia.peanut.task.engine.exec.JavaTaskBeanExec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component("test")
public class TestJavaTaskBeanExec implements JavaTaskBeanExec {
  @Override
  public Map<String, Object> exec(ExecTaskReq req) {
    log.info("exec");
    return new HashMap<>(Map.of("result", "success", "now", LocalDateTime.now()));
  }
}
