package com.olivia.peanut.base.api.entity.baseUserResource;

import java.time.LocalDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 用户角色资源表(BaseUserResource)表实体类
 *
 * @author peanut
 * @since 2024-08-09 16:26:39
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseUserResourceUpdateByIdReq extends BaseUserResourceDto {


  public void checkParam() {
  }

}
