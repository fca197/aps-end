package com.olivia.peanut.task.engine.entity;

import com.olivia.peanut.task.model.TaskInstanceHistory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@ToString
@Getter
@AllArgsConstructor
public class TaskCheckRunnerReq {

  private TaskInstanceHistory currentTaskInstanceHistory;

  private List<TaskInfoDef> taskInfoDefList;

  private TaskInfoDef currentTaskInfoDef;

  private Map<String, Object> lastOutMap;

  private Runnable successRun;

}
