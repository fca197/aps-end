package com.olivia.peanut.portal.api.entity.jcxBuyPlan;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (JcxBuyPlan)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-03-24 20:27:10
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class JcxBuyPlanDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

