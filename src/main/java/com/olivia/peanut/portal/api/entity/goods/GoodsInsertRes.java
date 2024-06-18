package com.olivia.peanut.portal.api.entity.goods;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 商品信息(Goods)保存返回
 *
 * @author makejava
 * @since 2024-03-11 10:44:05
 */
@Accessors(chain = true)
@Getter
@Setter

public class GoodsInsertRes {

  /****
   * 写入行数
   */
  private int count;

}

