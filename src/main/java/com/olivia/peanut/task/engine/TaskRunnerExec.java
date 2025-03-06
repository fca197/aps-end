package com.olivia.peanut.task.engine;


import com.alibaba.fastjson2.JSONObject;
import com.olivia.peanut.task.engine.entity.ExecTaskReq;

public interface TaskRunnerExec {
  JSONObject exec(ExecTaskReq req);
}
