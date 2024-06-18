package com.olivia.peanut.aps.api.entity.apsGoods;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoods)保存返回
 *
 * @author peanut
 * @since 2024-03-29 16:11:23
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsInsertRes {

  /****
   * 写入行数
   */
  private int count;

}

