package com.olivia.peanut.aps.api.entity.apsGoodsBomBuyPlan;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * BOM 购买计划(ApsGoodsBomBuyPlan)查询对象入参
 *
 * @author peanut
 * @since 2024-06-05 14:35:30
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsBomBuyPlanQueryByIdListReq {

  private List<Long> idList;


  public void checkParam() {
  }

}

