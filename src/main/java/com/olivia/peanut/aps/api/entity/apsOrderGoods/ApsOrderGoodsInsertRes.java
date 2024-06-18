package com.olivia.peanut.aps.api.entity.apsOrderGoods;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsOrderGoods)保存返回
 *
 * @author peanut
 * @since 2024-04-09 13:19:36
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderGoodsInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

