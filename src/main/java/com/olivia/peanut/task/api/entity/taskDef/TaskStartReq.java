package com.olivia.peanut.task.api.entity.taskDef;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Map;

@Setter
@Getter
@Accessors(chain = true)
public class TaskStartReq {
  @NotNull
  private Long taskId;

  private String instanceContent;

  private Map<String, Object> reqMap;
}
