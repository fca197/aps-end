package com.olivia.peanut.base.api.entity.baseUserDept;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 用户部门表(BaseUserDept)查询对象返回
 *
 * @author peanut
 * @since 2024-07-31 14:36:01
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseUserDeptQueryListRes {

  /***
   * 返回对象列表
   */
  private List<BaseUserDeptDto> dataList;


}

