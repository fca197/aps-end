package com.olivia.peanut.aps.api.entity.apsSchedulingGoodsBomTotal;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 订单商品零件汇总表(ApsSchedulingGoodsBomTotal)查询对象入参
 *
 * @author peanut
 * @since 2024-06-02 22:04:08
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingGoodsBomTotalQueryByIdListReq {

  private List<Long> idList;


}

