package com.olivia.peanut.flow.api.entity.flowForm;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 工作流表单表(FlowForm)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-08-02 23:26:22
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class FlowFormDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

