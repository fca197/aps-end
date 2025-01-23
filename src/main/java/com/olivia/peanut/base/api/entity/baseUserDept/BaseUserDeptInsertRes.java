package com.olivia.peanut.base.api.entity.baseUserDept;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 用户部门表(BaseUserDept)保存返回
 *
 * @author peanut
 * @since 2024-07-31 14:36:01
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseUserDeptInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

