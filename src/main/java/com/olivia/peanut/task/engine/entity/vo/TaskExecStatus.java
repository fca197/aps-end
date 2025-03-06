package com.olivia.peanut.task.engine.entity.vo;

public enum TaskExecStatus {
  //0 开始 ，10 执行成功 ，  20 异常 ， 30 超时 ，40 其他
  BEGIN, SUCCESS, FAILURE_EXCEPTION, TIME_OUT
}
