package com.olivia.peanut.base.api.entity.baseRoleGroup;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 角色组表(BaseRoleGroup)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-07-31 14:33:36
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseRoleGroupDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

