package com.olivia.peanut.flow.api.entity.flowGroup;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 工作流组表(FlowGroup)保存返回
 *
 * @author peanut
 * @since 2024-08-01 10:43:53
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class FlowGroupImportRes {

  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

