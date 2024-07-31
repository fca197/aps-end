package com.olivia.peanut.base.api.entity.baseRoleResource;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 角色资源表(BaseRoleResource)保存返回
 *
 * @author peanut
 * @since 2024-07-31 14:34:06
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseRoleResourceInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

