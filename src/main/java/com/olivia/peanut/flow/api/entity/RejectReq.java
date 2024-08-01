package com.olivia.peanut.flow.api.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class RejectReq {

  @NotNull(message = "taskId不能为空")
  private String taskId;
  private String message;
}
