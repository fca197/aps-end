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
 * @since 2024-07-31 14:34:07
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseRoleResourceUpdateByIdReq extends BaseRoleResourceDto {


  public void checkParam() {
  }

}

