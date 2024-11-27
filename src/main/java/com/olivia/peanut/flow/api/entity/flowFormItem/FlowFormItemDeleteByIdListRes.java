package com.olivia.peanut.flow.api.entity.flowFormItem;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 工作流表单项表(FlowFormItem)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-08-02 23:26:25
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class FlowFormItemDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

