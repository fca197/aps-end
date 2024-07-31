package com.olivia.peanut.base.api.entity.baseUserRoleGroup;

import java.time.LocalDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 用户角色组表(BaseUserRoleGroup)表实体类
 *
 * @author peanut
 * @since 2024-07-31 14:36:04
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseUserRoleGroupUpdateByIdReq extends BaseUserRoleGroupDto {


  public void checkParam() {
  }

}
