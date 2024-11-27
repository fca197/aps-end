package com.olivia.peanut.aps.api.entity.apsMachine;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * aps 生产机器(ApsMachine)保存返回
 *
 * @author makejava
 * @since 2024-10-24 16:31:14
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsMachineInsertRes {
  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

