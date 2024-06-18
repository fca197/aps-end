package com.olivia.peanut.aps.api.entity.apsGoodsBomBuyPlan;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * BOM 购买计划(ApsGoodsBomBuyPlan)表实体类
 *
 * @author peanut
 * @since 2024-06-05 14:35:30
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsBomBuyPlanUpdateByIdReq extends ApsGoodsBomBuyPlanDto {


  public void checkParam() {
  }

}

