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
public class TaskUndoneReq {

  @NotBlank(message = "流程key不能为空")
  private String flowKey;
}
