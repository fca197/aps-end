package com.olivia.peanut.task.exec.impl;

import com.olivia.peanut.task.engine.TaskBeanExec;
import com.olivia.peanut.task.engine.entity.ExecTaskReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component("test")
public class TestTaskBeanExec implements TaskBeanExec {
  @Override
  public Map<String, Object> exec(ExecTaskReq req) {
    log.info("exec");
    return new HashMap<>(Map.of("result", "success", "now", LocalDateTime.now()));
  }
}
