package com.olivia.peanut.portal.api.entity.jcxBuyPlanItem;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (JcxBuyPlanItem)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-03-24 20:27:11
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class JcxBuyPlanItemDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

