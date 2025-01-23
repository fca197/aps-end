package com.olivia.peanut.base.api.entity.baseH3Code;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * H3对应的值(BaseH3Code)根据ID删除多个反参
 *
 * @author makejava
 * @since 2024-11-19 16:09:18
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseH3CodeDeleteByIdListRes {
  /***
   * 受影响行数
   */
  private int count;

}

