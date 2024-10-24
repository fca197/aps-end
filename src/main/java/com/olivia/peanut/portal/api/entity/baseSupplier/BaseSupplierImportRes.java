package com.olivia.peanut.portal.api.entity.baseSupplier;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (BaseSupplier)保存返回
 *
 * @author peanut
 * @since 2024-03-28 15:35:38
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseSupplierImportRes {

  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

