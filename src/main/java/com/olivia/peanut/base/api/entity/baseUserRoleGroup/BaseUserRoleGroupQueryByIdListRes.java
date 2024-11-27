package com.olivia.peanut.base.api.entity.baseUserRoleGroup;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 用户角色组表(BaseUserRoleGroup)查询对象返回
 *
 * @author peanut
 * @since 2024-07-31 14:36:04
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseUserRoleGroupQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<BaseUserRoleGroupDto> dataList;


}

