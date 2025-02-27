package com.olivia.peanut.task;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class TaskConfig {
  private Long taskId;
  private String prefixListener;
  private String suffixListener;
//  private String javaCode;
}
