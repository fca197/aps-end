package com.olivia.peanut.base.api.entity.baseRoleResource;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 角色资源表(BaseRoleResource)查询对象入参
 *
 * @author peanut
 * @since 2024-07-31 14:35:11
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseRoleResourceQueryByIdListReq {

  private List<Long> idList;


  public void checkParam() {
  }

}

