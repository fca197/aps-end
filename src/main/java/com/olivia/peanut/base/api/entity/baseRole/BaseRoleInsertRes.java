package com.olivia.peanut.base.api.entity.baseRole;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 角色表(BaseRole)保存返回
 *
 * @author peanut
 * @since 2024-07-31 14:33:35
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseRoleInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

