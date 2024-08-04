package com.olivia.peanut.flow.api.entity;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.camunda.bpm.engine.task.Comment;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class TaskHistoryListRes {

  protected String taskId;
  protected String assignee;
  protected String owner;
  protected String name;
  protected String description;
  protected Date dueDate;
  protected Date followUpDate;
  protected int priority;
  protected String parentTaskId;
  protected String deleteReason;
  protected String taskDefinitionKey;
  protected String activityInstanceId;

  private Comment messageComment;
}
