package com.olivia.peanut.flow.api.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class TaskDoneRes {

  String taskId;
  String assignee;
  String owner;
  String name;
  String description;
  Date dueDate;
  Date followUpDate;
  int priority;
  String parentTaskId;
  String deleteReason;
  String taskDefinitionKey;
  String activityInstanceId;
  String tenantId;
  String businessKey;
  String flowFormId;
  String processInstanceId;
}
