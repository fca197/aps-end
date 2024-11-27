package com.olivia.peanut.base.api.entity.baseDept;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 部门表(BaseDept)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-07-31 14:33:30
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseDeptDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

