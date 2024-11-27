package com.olivia.peanut.base.api.entity.baseRoleGroupResource;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 角色组资源表(BaseRoleGroupResource)保存返回
 *
 * @author peanut
 * @since 2024-08-09 15:42:34
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseRoleGroupResourceInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

