package com.olivia.peanut.base.api.entity.baseRoleGroup;

import java.time.LocalDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 角色组表(BaseRoleGroup)表实体类
 *
 * @author peanut
 * @since 2024-07-31 14:33:36
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseRoleGroupUpdateByIdReq extends BaseRoleGroupDto {


  public void checkParam() {
  }

}
