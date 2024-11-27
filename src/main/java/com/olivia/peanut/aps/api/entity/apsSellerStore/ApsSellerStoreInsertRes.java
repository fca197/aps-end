package com.olivia.peanut.aps.api.entity.apsSellerStore;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * aps销售门店(ApsSellerStore)保存返回
 *
 * @author makejava
 * @since 2024-11-15 14:58:58
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSellerStoreInsertRes {
  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

