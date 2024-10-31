package com.olivia.peanut.flow.api.entity.flowFormItem;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 工作流表单项表(FlowFormItem)查询对象返回
 *
 * @author peanut
 * @since 2024-08-02 23:26:26
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class FlowFormItemQueryListRes {

  /***
   * 返回对象列表
   */
  private List<FlowFormItemDto> dataList;


}

