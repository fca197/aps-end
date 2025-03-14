package com.olivia.peanut.base.api.entity.baseTableHeader;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (BaseTableHeader)保存返回
 *
 * @author peanut
 * @since 2024-03-25 14:19:09
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseTableHeaderInsertRes {

  /****
   * 写入行数
   */
  private int count;

}

