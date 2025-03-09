package com.olivia.peanut.task.engine.impl;

import cn.hutool.extra.spring.SpringUtil;
import com.google.common.collect.Lists;
import com.olivia.peanut.task.engine.entity.ExecTaskReq;
import com.olivia.peanut.task.engine.entity.TaskInfoDef;
import com.olivia.peanut.task.engine.exec.TaskRunnerExec;
import com.olivia.peanut.task.exec.impl.DouBaoAITaskBeanExec;
import com.olivia.sdk.exception.RunException;
import com.olivia.sdk.model.KVEntity;
import com.olivia.sdk.utils.Str;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.util.Map;

@Setter
@Getter
@Accessors(chain = true)
@Component("ai" + "TaskRunnerExec")
public class AITaskRunnerExec implements TaskRunnerExec {
  @Override
  public KVEntity getTaskRunnerExecName() {
    return KVEntity.of(Str.DEFAULT_ZN, Str.DEFAULT).setChildrenList(Lists.newArrayList(KVEntity.of("AI", "AI")));
  }

  @Override
  public Map<String, Object> exec(ExecTaskReq req) {
    if ("read".equals(SpringUtil.getActiveProfile())) {
      throw new RunException("只读环境，禁止使用AI功能");
    }
    TaskInfoDef taskInfoDef = req.getCurrentTaskInfoDef();
    String taskAiName = taskInfoDef.getTaskAiName();
    DouBaoAITaskBeanExec taskBeanExec = SpringUtil.getBean(taskAiName, DouBaoAITaskBeanExec.class);
    return taskBeanExec.exec(req);
  }
}
