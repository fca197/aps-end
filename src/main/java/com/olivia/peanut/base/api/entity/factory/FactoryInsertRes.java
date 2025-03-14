package com.olivia.peanut.base.api.entity.factory;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 工段信息(Factory)保存返回
 *
 * @author makejava
 * @since 2024-03-11 10:44:04
 */
@Accessors(chain = true)
@Getter
@Setter

public class FactoryInsertRes {

  /****
   * 写入行数
   */
  private int count;

}

