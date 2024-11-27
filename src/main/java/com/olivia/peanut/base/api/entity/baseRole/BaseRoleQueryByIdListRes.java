package com.olivia.peanut.base.api.entity.baseRole;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 角色表(BaseRole)查询对象返回
 *
 * @author peanut
 * @since 2024-07-31 14:33:35
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseRoleQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<BaseRoleDto> dataList;


}

