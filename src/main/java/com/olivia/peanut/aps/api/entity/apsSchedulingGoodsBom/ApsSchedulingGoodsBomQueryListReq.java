package com.olivia.peanut.aps.api.entity.apsSchedulingGoodsBom;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 订单商品零件表(ApsSchedulingGoodsBom)查询对象入参
 *
 * @author peanut
 * @since 2024-06-02 21:50:24
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingGoodsBomQueryListReq {

  private ApsSchedulingGoodsBomDto data;
}

