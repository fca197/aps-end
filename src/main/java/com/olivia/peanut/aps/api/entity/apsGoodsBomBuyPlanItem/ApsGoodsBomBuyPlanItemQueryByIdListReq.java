package com.olivia.peanut.aps.api.entity.apsGoodsBomBuyPlanItem;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * BOM 购买清单(ApsGoodsBomBuyPlanItem)查询对象入参
 *
 * @author peanut
 * @since 2024-06-05 14:35:31
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsBomBuyPlanItemQueryByIdListReq {

  private List<Long> idList;


  public void checkParam() {
  }

}

