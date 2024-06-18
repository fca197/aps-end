package com.olivia.peanut.aps.api.entity.apsSchedulingConstraints;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsSchedulingConstraints)保存返回
 *
 * @author peanut
 * @since 2024-04-13 21:32:13
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingConstraintsImportRes {

  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

