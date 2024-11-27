package com.olivia.peanut.aps.api.entity.apsSchedulingVersionDay;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * (ApsSchedulingVersionDay)保存返回
 *
 * @author peanut
 * @since 2024-04-23 14:37:05
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingVersionDayImportRes {

  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

