package com.olivia.peanut.flow.api.entity.flowForm;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 工作流表单表(FlowForm)保存返回
 *
 * @author peanut
 * @since 2024-08-02 23:26:21
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class FlowFormInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

