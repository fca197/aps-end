package com.olivia.peanut.flow.api.entity.flowGroup;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 工作流组表(FlowGroup)查询对象返回
 *
 * @author peanut
 * @since 2024-08-01 10:43:53
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class FlowGroupQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<FlowGroupDto> dataList;


}

