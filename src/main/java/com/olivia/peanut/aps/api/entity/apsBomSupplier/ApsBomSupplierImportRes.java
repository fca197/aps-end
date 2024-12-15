package com.olivia.peanut.aps.api.entity.apsBomSupplier;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 供应商表(ApsBomSupplier)保存返回
 *
 * @author makejava
 * @since 2024-12-15 14:39:45
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsBomSupplierImportRes {
  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

