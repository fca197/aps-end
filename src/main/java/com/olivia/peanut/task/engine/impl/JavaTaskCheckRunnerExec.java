package com.olivia.peanut.task.engine.impl;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.olivia.peanut.task.engine.entity.TaskCheckRunnerReq;
import com.olivia.peanut.task.engine.entity.TaskInfoDef;
import com.olivia.peanut.task.engine.entity.vo.TaskJavaType;
import com.olivia.peanut.task.engine.exec.JavaTaskCheckBeanExec;
import com.olivia.peanut.task.engine.exec.TaskCheckRunnerExec;
import org.springframework.stereotype.Component;

@Component("java" + "TaskCheckRunnerExec")
public class JavaTaskCheckRunnerExec implements TaskCheckRunnerExec {
  @Override
  public void exec(TaskCheckRunnerReq req) {
    TaskInfoDef taskInfoDef = req.getCurrentTaskInfoDef();
    JavaTaskCheckBeanExec javaTaskCheckBeanExec = null;
    if (TaskJavaType.springBean.equals(taskInfoDef.getCheckTaskJavaType())) {
      javaTaskCheckBeanExec = SpringUtil.getBean(taskInfoDef.getCheckTaskBeanName(), JavaTaskCheckBeanExec.class);
    } else {
      javaTaskCheckBeanExec = ReflectUtil.newInstance(taskInfoDef.getCheckTaskBeanName());
    }
    boolean execBool = javaTaskCheckBeanExec.exec(req);
    if (!execBool) {
      failToNexLoop(req);
    }
  }
}
