package com.olivia.peanut.base.api.entity.baseRoleGroup;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 角色组表(BaseRoleGroup)保存入参
 *
 * @author peanut
 * @since 2024-07-31 14:33:36
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseRoleGroupInsertReq extends BaseRoleGroupDto {

  public void checkParam() {
  }
}

