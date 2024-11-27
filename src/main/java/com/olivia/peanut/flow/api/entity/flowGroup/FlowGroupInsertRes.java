package com.olivia.peanut.flow.api.entity.flowGroup;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 工作流组表(FlowGroup)保存返回
 *
 * @author peanut
 * @since 2024-08-01 10:43:52
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class FlowGroupInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

