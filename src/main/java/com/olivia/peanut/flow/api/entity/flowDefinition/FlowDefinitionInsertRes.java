package com.olivia.peanut.flow.api.entity.flowDefinition;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 工作定义表(FlowDefinition)保存返回
 *
 * @author peanut
 * @since 2024-08-01 10:43:49
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class FlowDefinitionInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

