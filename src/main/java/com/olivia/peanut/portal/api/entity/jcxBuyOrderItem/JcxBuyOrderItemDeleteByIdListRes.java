package com.olivia.peanut.portal.api.entity.jcxBuyOrderItem;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (JcxBuyOrderItem)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-03-27 13:51:37
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class JcxBuyOrderItemDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

