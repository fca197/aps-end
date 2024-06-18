package com.olivia.peanut.aps.api.entity.apsOrderGoods;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsOrderGoods)查询对象入参
 *
 * @author peanut
 * @since 2024-04-09 13:19:36
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderGoodsQueryListReq {

  private ApsOrderGoodsDto data;
}

