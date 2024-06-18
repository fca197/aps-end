package com.olivia.peanut.portal.api.entity.jcxBuyOrder;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (JcxBuyOrder)保存返回
 *
 * @author peanut
 * @since 2024-03-27 13:51:36
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class JcxBuyOrderInsertRes {

  /****
   * 写入行数
   */
  private int count;
  private Long id;

  private String msg;
}

