package com.olivia.peanut.aps.api.entity.apsSchedulingGoodsStatusDate;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 订单商品状态表(ApsSchedulingGoodsStatusDate)查询对象入参
 *
 * @author peanut
 * @since 2024-06-14 21:35:09
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingGoodsStatusDateQueryByIdListReq {

  private List<Long> idList;


}

