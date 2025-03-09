package com.olivia.peanut.task.exec.impl;

import com.google.common.collect.Lists;
import com.olivia.peanut.task.engine.entity.ExecTaskReq;
import com.olivia.peanut.task.engine.exec.AITaskBeanExec;
import com.olivia.sdk.model.KVEntity;
import com.olivia.sdk.utils.Str;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("deepSeekAITaskBeanExec")
public class DeepSeekAITaskBeanExec implements AITaskBeanExec {
  @Override
  public void preData(ExecTaskReq execTaskReq) {

  }

  @Override
  public KVEntity getAITaskBeanExecName() {
    return KVEntity.of(Str.DEFAULT_ZN, Str.DEFAULT).setChildrenList(Lists.newArrayList(KVEntity.of("测试java执行", "deepSeekAITaskBeanExec")));
  }

  @Override
  public Map<String, Object> exec(ExecTaskReq req) {
    return Map.of();
  }
}
