package com.olivia.peanut.base.api.entity.baseRoleGroupResource;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 角色组资源表(BaseRoleGroupResource)保存入参
 *
 * @author peanut
 * @since 2024-08-09 15:42:34
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseRoleGroupResourceInsertReq extends BaseRoleGroupResourceDto {

  public void checkParam() {
  }
}

