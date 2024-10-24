package com.olivia.peanut.base.api.entity.baseRoleResource;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 角色资源表(BaseRoleResource)查询对象返回
 *
 * @author peanut
 * @since 2024-08-09 15:42:37
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseRoleResourceQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<BaseRoleResourceDto> dataList;


}

