package com.olivia.peanut.flow.core.listener;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class DelegateTaskInfo {

  private String processDefinitionName;
  private String taskId;
  private String taskName;
  private String processDefinitionId;
  private String processInstanceId;


}
