package com.olivia.peanut.task.engine.impl;

import cn.hutool.extra.spring.SpringUtil;
import com.olivia.peanut.task.engine.entity.ExecTaskReq;
import com.olivia.peanut.task.engine.entity.TaskInfoDef;
import com.olivia.peanut.task.engine.exec.JavaTaskBeanExec;
import com.olivia.peanut.task.engine.exec.TaskRunnerExec;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("java_bean" + "TaskRunnerExec")
public class JavaBeanTaskRunnerExecImpl implements TaskRunnerExec {
  @Override
  public Map<String, Object> exec(ExecTaskReq req) {
    TaskInfoDef currentTaskInfoDef = req.getCurrentTaskInfoDef();
    String taskBeanName = currentTaskInfoDef.getTaskBeanName();
    JavaTaskBeanExec javaTaskBeanExec = SpringUtil.getBean(taskBeanName, JavaTaskBeanExec.class);
    return javaTaskBeanExec.exec(req);
  }
}
