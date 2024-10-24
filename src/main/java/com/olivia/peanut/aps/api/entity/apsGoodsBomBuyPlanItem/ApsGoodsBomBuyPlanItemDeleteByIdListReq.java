package com.olivia.peanut.aps.api.entity.apsGoodsBomBuyPlanItem;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * BOM 购买清单(ApsGoodsBomBuyPlanItem)根据ID删除多个入参
 *
 * @author peanut
 * @since 2024-06-05 14:35:31
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsBomBuyPlanItemDeleteByIdListReq {

  /***
   * 要删除的ID
   */
  @NotEmpty(message = "请选择删除对象")
  private List<Long> idList;


  public void checkParam() {
  }

}

