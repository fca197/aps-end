package com.olivia.peanut.task.engine.entity.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class Mapping {
  private String source;
  private String target;
  private MappingType mappingType;
  private String javaExpression;
}
