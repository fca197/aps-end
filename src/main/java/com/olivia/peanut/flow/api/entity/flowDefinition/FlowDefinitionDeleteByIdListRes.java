package com.olivia.peanut.flow.api.entity.flowDefinition;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 工作定义表(FlowDefinition)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-08-01 10:43:49
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class FlowDefinitionDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

