package com.olivia.peanut.flow.api.entity.flowDefinition;

import java.time.LocalDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 工作定义表(FlowDefinition)表实体类
 *
 * @author peanut
 * @since 2024-08-01 10:43:49
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class FlowDefinitionUpdateByIdReq extends FlowDefinitionDto {


  public void checkParam() {
  }

}

