package com.olivia.peanut.aps.api.entity.apsBomSupplier;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 供应商表(ApsBomSupplier)保存返回
 *
 * @author makejava
 * @since 2024-12-15 14:39:44
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsBomSupplierInsertRes {
  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

