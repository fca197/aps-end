package com.olivia.peanut.task.listener;

import com.google.common.collect.Lists;
import com.olivia.peanut.task.engine.entity.ExecTaskReq;
import com.olivia.peanut.task.engine.listener.TaskListener;
import com.olivia.sdk.model.KVEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("test2-2")
public class Test2TaskListener implements TaskListener {
  @Override
  public KVEntity getTaskListenerName() {
    return KVEntity.of("测试", "test").setChildrenList(Lists.newArrayList(KVEntity.of("监听器2", "test2-2")));
  }

  @Override
  public void execListener(ExecTaskReq req) {
    log.info("监听器执行");
  }
}
