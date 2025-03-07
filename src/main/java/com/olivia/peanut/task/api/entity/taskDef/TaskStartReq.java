package com.olivia.peanut.task.api.entity.taskDef;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class TaskStartReq {
  @NotNull
  private Long taskId;
}
