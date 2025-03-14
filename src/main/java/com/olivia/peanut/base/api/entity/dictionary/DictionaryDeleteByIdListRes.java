package com.olivia.peanut.base.api.entity.dictionary;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 字典值(Dictionary)根据ID删除多个反参
 *
 * @author makejava
 * @since 2024-03-11 10:44:04
 */
@Accessors(chain = true)
@Getter
@Setter

public class DictionaryDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

