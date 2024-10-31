package com.olivia.peanut.base.api.entity.baseRoleGroupResource;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 角色组资源表(BaseRoleGroupResource)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-08-09 15:42:34
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseRoleGroupResourceDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

