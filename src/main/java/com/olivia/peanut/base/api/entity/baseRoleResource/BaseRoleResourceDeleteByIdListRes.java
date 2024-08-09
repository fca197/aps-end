package com.olivia.peanut.base.api.entity.baseRoleResource;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 角色资源表(BaseRoleResource)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-08-09 15:42:36
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseRoleResourceDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

