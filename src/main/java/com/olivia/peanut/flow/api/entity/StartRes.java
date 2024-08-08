package com.olivia.peanut.flow.api.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class StartRes {

  private String flowKey;
  private String processInstanceId;
  private Long flowFormId;
  private String businessKey;
  private String taskId;
}
