package com.olivia.peanut.base.api.entity.baseRole;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 角色表(BaseRole)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-07-31 14:33:35
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseRoleDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

