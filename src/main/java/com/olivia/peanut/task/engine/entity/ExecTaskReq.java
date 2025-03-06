package com.olivia.peanut.task.engine.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class ExecTaskReq {

  public Long instanceId;

  private Long lastTaskInstanceId;
  private Long taskInstanceId;

  private List<TaskInfoDef> taskInfoDefList;

  private TaskInfoDef currentTaskInfoDef;

  Map<String, Object> lastOutMap;
}
