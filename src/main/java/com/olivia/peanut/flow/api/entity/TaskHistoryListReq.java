package com.olivia.peanut.flow.api.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class TaskHistoryListReq {

  //  @NotBlank(message = "任务id不能为空")
  private String taskId;
  @NotBlank(message = "流程实例id不能为空")
  private String processInstanceId;
}
