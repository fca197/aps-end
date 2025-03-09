package com.olivia.peanut.task.exec.impl;

import com.google.common.collect.Lists;
import com.olivia.peanut.task.engine.entity.ExecTaskReq;
import com.olivia.peanut.task.engine.exec.JavaTaskBeanExec;
import com.olivia.sdk.model.KVEntity;
import com.olivia.sdk.utils.Str;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component("testJavaTaskBeanExec")
public class TestJavaTaskBeanExec implements JavaTaskBeanExec {
  @Override
  public KVEntity getJavaTaskBeanExecName() {
    return KVEntity.of(Str.DEFAULT_ZN, Str.DEFAULT).setChildrenList(Lists.newArrayList(KVEntity.of("测试java执行", "testJavaTaskBeanExec")));
  }

  @Override
  public Map<String, Object> exec(ExecTaskReq req) {
    log.info("exec");
    return new HashMap<>(Map.of("result", "success", "now", LocalDateTime.now()));
  }
}
