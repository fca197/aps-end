package com.olivia.peanut.task.engine.impl;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.google.common.collect.Lists;
import com.olivia.peanut.task.engine.entity.TaskCheckRunnerReq;
import com.olivia.peanut.task.engine.entity.TaskInfoDef;
import com.olivia.peanut.task.engine.entity.vo.TaskJavaType;
import com.olivia.peanut.task.engine.exec.JavaTaskCheckBeanExec;
import com.olivia.peanut.task.engine.exec.TaskCheckRunnerExec;
import com.olivia.sdk.model.KVEntity;
import com.olivia.sdk.utils.Str;
import org.springframework.stereotype.Component;

@Component("javaTaskCheckRunnerExec")
public class JavaTaskCheckRunnerExec implements TaskCheckRunnerExec {
  @Override
  public KVEntity getTaskCheckRunnerName() {
    return KVEntity.of(Str.DEFAULT_ZN, Str.DEFAULT).setChildrenList(Lists.newArrayList(KVEntity.of("JAVA", "javaTaskCheckRunnerExec")));
  }

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
