package com.olivia.peanut.flow.api.entity.flowFormItem;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 工作流表单项表(FlowFormItem)保存返回
 *
 * @author peanut
 * @since 2024-08-02 23:26:25
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class FlowFormItemInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

