package com.olivia.peanut.base.api.entity.baseDept;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 部门表(BaseDept)保存返回
 *
 * @author peanut
 * @since 2024-07-31 14:33:30
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseDeptInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

