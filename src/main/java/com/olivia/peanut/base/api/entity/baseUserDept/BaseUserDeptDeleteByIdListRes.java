package com.olivia.peanut.base.api.entity.baseUserDept;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 用户部门表(BaseUserDept)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-07-31 14:36:01
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseUserDeptDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

