package com.olivia.peanut.task.engine.impl;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.google.common.collect.Lists;
import com.olivia.peanut.task.engine.entity.ExecTaskReq;
import com.olivia.peanut.task.engine.entity.TaskInfoDef;
import com.olivia.peanut.task.engine.entity.vo.TaskJavaType;
import com.olivia.peanut.task.engine.exec.JavaTaskBeanExec;
import com.olivia.peanut.task.engine.exec.TaskRunnerExec;
import com.olivia.sdk.model.KVEntity;
import com.olivia.sdk.utils.Str;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("javaBeanTaskRunnerExec")
public class JavaBeanTaskRunnerExecImpl implements TaskRunnerExec {

  @Override
  public KVEntity getTaskRunnerExecName() {
    return KVEntity.of(Str.DEFAULT_ZN, Str.DEFAULT).setChildrenList(Lists.newArrayList(KVEntity.of("JAVA", "javaBeanTaskRunnerExec")));
  }

  @Override
  public Map<String, Object> exec(ExecTaskReq req) {
    TaskInfoDef currentTaskInfoDef = req.getCurrentTaskInfoDef();
    TaskJavaType taskJavaType = currentTaskInfoDef.getTaskJavaType();
    String taskBeanName = currentTaskInfoDef.getTaskBeanName();

    JavaTaskBeanExec javaTaskBeanExec;
    if (TaskJavaType.javaClass.equals(taskJavaType)) {
      javaTaskBeanExec = ReflectUtil.newInstance(taskBeanName);
    } else {
      javaTaskBeanExec = SpringUtil.getBean(taskBeanName, JavaTaskBeanExec.class);
    }
    return javaTaskBeanExec.exec(req);
  }
}
