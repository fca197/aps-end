package com.olivia.peanut.aps.api.entity.apsBomSupplier;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 供应商表(ApsBomSupplier)根据ID删除多个反参
 *
 * @author makejava
 * @since 2024-12-15 14:39:44
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsBomSupplierDeleteByIdListRes {
  /***
   * 受影响行数
   */
  private int count;

}

