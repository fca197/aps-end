package com.olivia.peanut.base.api.entity.baseUserRole;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 用户角色表(BaseUserRole)查询对象入参
 *
 * @author peanut
 * @since 2024-07-31 14:36:03
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseUserRoleQueryByIdListReq {

  private List<Long> idList;


  public void checkParam() {
  }

}

