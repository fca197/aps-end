package com.olivia.peanut.aps.api.entity.apsSchedulingGoodsBom;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 订单商品零件表(ApsSchedulingGoodsBom)根据ID删除多个入参
 *
 * @author peanut
 * @since 2024-06-02 21:50:24
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingGoodsBomDeleteByIdListReq {

  /***
   * 要删除的ID
   */
  @NotEmpty(message = "请选择删除对象")
  private List<Long> idList;


}

