package com.olivia.peanut.base.api.entity.baseRoleResource;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 角色资源表(BaseRoleResource)查询对象入参
 *
 * @author peanut
 * @since 2024-07-31 14:34:57
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseRoleResourceExportQueryPageListReq {

  private int pageNum;
  private int pageSize;
  private Boolean queryPage = true;
  private BaseRoleResourceDto data;


  public void checkParam() {
  }

}

