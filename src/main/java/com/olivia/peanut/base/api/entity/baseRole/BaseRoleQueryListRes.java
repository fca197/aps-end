package com.olivia.peanut.base.api.entity.baseRole;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
public class BaseRoleQueryListRes {

  /***
   * 返回对象列表
   */
  private List<BaseRoleDto> dataList;


}

