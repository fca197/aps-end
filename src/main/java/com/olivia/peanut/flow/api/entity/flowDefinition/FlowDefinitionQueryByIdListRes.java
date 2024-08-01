package com.olivia.peanut.flow.api.entity.flowDefinition;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 工作定义表(FlowDefinition)查询对象返回
 *
 * @author peanut
 * @since 2024-08-01 10:43:49
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class FlowDefinitionQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<FlowDefinitionDto> dataList;


}

