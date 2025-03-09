package com.olivia.peanut.task.engine.exec;

import com.olivia.peanut.task.engine.entity.ExecTaskReq;
import com.olivia.sdk.model.KVEntity;

import java.util.Map;

public interface JavaTaskBeanExec {

  KVEntity getJavaTaskBeanExecName();

  Map<String, Object> exec(ExecTaskReq req);
}
