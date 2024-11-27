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
public class TaskUndoneRes {

  private String taskId;
  private String name;
  private String packageName;
  private Date createTime; // The time when the task has been created
  private Date lastUpdated;
  private Date dueDate;
  private Date followUpDate;
  private String processInstanceId;
  private String businessKey;
  private Object flowFormId;
  private Boolean isFirstTask;
}
