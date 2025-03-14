package com.olivia.peanut.base.api.entity.baseTableHeader;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (BaseTableHeader)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-03-25 14:19:09
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseTableHeaderDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

