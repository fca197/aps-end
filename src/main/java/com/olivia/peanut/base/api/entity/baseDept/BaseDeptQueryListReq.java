package com.olivia.peanut.base.api.entity.baseDept;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 部门表(BaseDept)查询对象入参
 *
 * @author peanut
 * @since 2024-07-31 14:33:30
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseDeptQueryListReq {

  private BaseDeptDto data;
}

