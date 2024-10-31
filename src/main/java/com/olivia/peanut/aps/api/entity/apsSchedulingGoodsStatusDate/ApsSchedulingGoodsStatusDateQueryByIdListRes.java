package com.olivia.peanut.aps.api.entity.apsSchedulingGoodsStatusDate;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 订单商品状态表(ApsSchedulingGoodsStatusDate)查询对象返回
 *
 * @author peanut
 * @since 2024-06-14 21:35:10
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingGoodsStatusDateQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<ApsSchedulingGoodsStatusDateDto> dataList;


}

