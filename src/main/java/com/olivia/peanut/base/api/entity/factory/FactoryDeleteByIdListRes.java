package com.olivia.peanut.base.api.entity.factory;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 工段信息(Factory)根据ID删除多个反参
 *
 * @author makejava
 * @since 2024-03-11 10:44:04
 */
@Accessors(chain = true)
@Getter
@Setter

public class FactoryDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

