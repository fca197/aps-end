package com.olivia.peanut.portal.api.entity.jcxOrderItem;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (JcxOrderItem)保存返回
 *
 * @author peanut
 * @since 2024-03-22 10:38:07
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class JcxOrderItemInsertRes {

  /****
   * 写入行数
   */
  private int count;

}

