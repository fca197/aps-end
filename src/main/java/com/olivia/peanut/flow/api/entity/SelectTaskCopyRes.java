package com.olivia.peanut.flow.api.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class SelectTaskCopyRes {

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
