package com.olivia.peanut.flow.api.entity.flowGroup;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 工作流组表(FlowGroup)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-08-01 10:43:52
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class FlowGroupDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

