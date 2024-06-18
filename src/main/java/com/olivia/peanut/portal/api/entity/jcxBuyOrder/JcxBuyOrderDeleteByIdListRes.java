package com.olivia.peanut.portal.api.entity.jcxBuyOrder;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (JcxBuyOrder)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-03-27 13:51:36
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class JcxBuyOrderDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

