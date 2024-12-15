package com.olivia.peanut.aps.api.entity.apsBomSupplier;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 供应商表(ApsBomSupplier)保存入参
 *
 * @author makejava
 * @since 2024-12-15 14:39:44
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsBomSupplierInsertReq extends ApsBomSupplierDto {

  public void checkParam() {
  }
}

