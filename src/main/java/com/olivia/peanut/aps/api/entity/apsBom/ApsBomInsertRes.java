package com.olivia.peanut.aps.api.entity.apsBom;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * BOM 清单(ApsBom)保存返回
 *
 * @author peanut
 * @since 2024-06-06 11:27:34
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsBomInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

