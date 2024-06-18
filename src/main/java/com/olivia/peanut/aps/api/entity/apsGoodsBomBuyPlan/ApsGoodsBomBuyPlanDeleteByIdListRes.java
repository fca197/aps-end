package com.olivia.peanut.aps.api.entity.apsGoodsBomBuyPlan;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * BOM 购买计划(ApsGoodsBomBuyPlan)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-06-05 14:35:29
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsBomBuyPlanDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

