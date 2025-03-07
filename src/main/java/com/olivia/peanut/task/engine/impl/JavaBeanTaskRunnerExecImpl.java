package com.olivia.peanut.task.engine.impl;

import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson2.JSONObject;
import com.olivia.peanut.task.engine.TaskBeanExec;
import com.olivia.peanut.task.engine.TaskRunnerExec;
import com.olivia.peanut.task.engine.entity.ExecTaskReq;
import com.olivia.peanut.task.engine.entity.TaskInfoDef;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("java_bean" + "TaskRunnerExec")
public class JavaBeanTaskRunnerExecImpl implements TaskRunnerExec {
  @Override
  public Map<String, Object> exec(ExecTaskReq req) {
    TaskInfoDef currentTaskInfoDef = req.getCurrentTaskInfoDef();
    String taskBeanName = currentTaskInfoDef.getTaskBeanName();
    TaskBeanExec taskBeanExec = SpringUtil.getBean(taskBeanName, TaskBeanExec.class);
    return taskBeanExec.exec(req);
  }
}
