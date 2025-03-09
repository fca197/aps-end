package com.olivia.peanut.task.exec.impl;

import com.google.common.collect.Lists;
import com.olivia.peanut.task.engine.entity.ExecTaskReq;
import com.olivia.peanut.task.engine.exec.AITaskBeanExec;
import com.olivia.peanut.task.utils.DouBaoUtils;
import com.olivia.sdk.model.KVEntity;
import com.olivia.sdk.utils.Str;
import com.volcengine.ark.runtime.service.ArkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component("douBaoAITaskBeanExec")
public class DouBaoAITaskBeanExec implements AITaskBeanExec {


  @Override
  public void preData(ExecTaskReq execTaskReq) {
    ArkService arkService = DouBaoUtils.getArkService();


  }

  @Override
  public KVEntity getAITaskBeanExecName() {
    return KVEntity.of(Str.DEFAULT_ZN, Str.DEFAULT).setChildrenList(Lists.newArrayList(KVEntity.of("测试java执行", "douBaoAITaskBeanExec")));
  }

  @Override
  public Map<String, Object> exec(ExecTaskReq req) {
    return Map.of();
  }
}
