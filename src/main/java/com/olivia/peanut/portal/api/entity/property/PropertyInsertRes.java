package com.olivia.peanut.portal.api.entity.property;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 资产信息(Property)保存返回
 *
 * @author makejava
 * @since 2024-03-11 17:20:52
 */
@Accessors(chain = true)
@Getter
@Setter

public class PropertyInsertRes {

  /****
   * 写入行数
   */
  private int count;
  private Long id;

}

