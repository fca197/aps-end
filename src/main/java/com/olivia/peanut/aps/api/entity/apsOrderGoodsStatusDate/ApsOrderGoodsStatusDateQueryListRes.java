package com.olivia.peanut.aps.api.entity.apsOrderGoodsStatusDate;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 订单商品状态表(ApsOrderGoodsStatusDate)查询对象返回
 *
 * @author peanut
 * @since 2024-06-14 10:26:58
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderGoodsStatusDateQueryListRes {

  /***
   * 返回对象列表
   */
  private List<ApsOrderGoodsStatusDateDto> dataList;


}

