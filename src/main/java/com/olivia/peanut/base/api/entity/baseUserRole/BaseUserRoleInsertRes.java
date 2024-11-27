package com.olivia.peanut.base.api.entity.baseUserRole;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 用户角色表(BaseUserRole)保存返回
 *
 * @author peanut
 * @since 2024-07-31 14:36:03
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseUserRoleInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

