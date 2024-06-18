package com.olivia.peanut.portal.api.entity.jcxOrderItem;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (JcxOrderItem)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-03-22 10:38:07
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class JcxOrderItemDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

