package com.olivia.peanut.base.api.entity.baseUserRoleGroup;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 用户角色组表(BaseUserRoleGroup)保存返回
 *
 * @author peanut
 * @since 2024-07-31 14:36:04
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseUserRoleGroupImportRes {

  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

