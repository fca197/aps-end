package com.olivia.peanut.base.api.entity.baseRoleGroup;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 角色组表(BaseRoleGroup)查询对象入参
 *
 * @author peanut
 * @since 2024-07-31 14:33:36
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseRoleGroupExportQueryPageListReq {

  private int pageNum;
  private int pageSize;
  private Boolean queryPage = true;
  private BaseRoleGroupDto data;


}

