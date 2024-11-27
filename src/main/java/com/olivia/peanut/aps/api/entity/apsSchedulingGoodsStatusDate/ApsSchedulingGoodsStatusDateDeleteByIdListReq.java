package com.olivia.peanut.aps.api.entity.apsSchedulingGoodsStatusDate;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 订单商品状态表(ApsSchedulingGoodsStatusDate)根据ID删除多个入参
 *
 * @author peanut
 * @since 2024-06-14 21:35:09
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingGoodsStatusDateDeleteByIdListReq {

  /***
   * 要删除的ID
   */
  @NotEmpty(message = "请选择删除对象")
  private List<Long> idList;


}

