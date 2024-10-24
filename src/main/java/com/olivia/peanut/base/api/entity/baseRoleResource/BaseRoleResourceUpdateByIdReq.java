package com.olivia.peanut.base.api.entity.baseRoleResource;

import java.time.LocalDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 角色资源表(BaseRoleResource)表实体类
 *
 * @author peanut
 * @since 2024-08-09 15:42:36
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseRoleResourceUpdateByIdReq extends BaseRoleResourceDto {


  public void checkParam() {
  }

}

