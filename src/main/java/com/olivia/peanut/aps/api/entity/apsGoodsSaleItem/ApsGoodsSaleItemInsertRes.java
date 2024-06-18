package com.olivia.peanut.aps.api.entity.apsGoodsSaleItem;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsSaleItem)保存返回
 *
 * @author peanut
 * @since 2024-03-30 10:38:36
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsSaleItemInsertRes {

  /****
   * 写入行数
   */
  private int count;

}

