package com.olivia.peanut.base.api.entity.baseUserResource;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 用户角色资源表(BaseUserResource)查询对象入参
 *
 * @author peanut
 * @since 2024-08-09 16:26:40
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseUserResourceExportQueryPageListReq {

  private int pageNum;
  private int pageSize;
  private Boolean queryPage = true;
  private BaseUserResourceDto data;


  public void checkParam() {
  }

}

