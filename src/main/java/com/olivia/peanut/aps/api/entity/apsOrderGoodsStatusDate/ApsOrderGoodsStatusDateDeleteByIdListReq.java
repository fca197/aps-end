package com.olivia.peanut.aps.api.entity.apsOrderGoodsStatusDate;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 订单商品状态表(ApsOrderGoodsStatusDate)根据ID删除多个入参
 *
 * @author peanut
 * @since 2024-06-14 10:26:58
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderGoodsStatusDateDeleteByIdListReq {

  /***
   * 要删除的ID
   */
  @NotEmpty(message = "请选择删除对象")
  private List<Long> idList;


}

