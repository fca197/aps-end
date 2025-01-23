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
public class TaskHistoryListRes {

  private String id;
  private String taskId;
  private String assignee;
  private String owner;
  private String name;
  private String description;
  private Date dueDate;
  private Date followUpDate;
  private int priority;
  private String parentTaskId;
  private String deleteReason;
  private String taskDefinitionKey;
  private String activityInstanceId;

  //  private Comment messageComment;
  private String message;

}
