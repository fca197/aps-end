package com.olivia.peanut.flow.api.entity.flowForm;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 工作流表单表(FlowForm)查询对象返回
 *
 * @author peanut
 * @since 2024-08-02 23:26:22
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class FlowFormQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<FlowFormDto> dataList;


}

