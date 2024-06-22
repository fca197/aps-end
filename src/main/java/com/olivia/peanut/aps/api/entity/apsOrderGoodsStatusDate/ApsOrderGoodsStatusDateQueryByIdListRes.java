package com.olivia.peanut.aps.api.entity.apsOrderGoodsStatusDate;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 订单商品状态表(ApsOrderGoodsStatusDate)查询对象返回
 *
 * @author peanut
 * @since 2024-06-14 10:26:59
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderGoodsStatusDateQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<ApsOrderGoodsStatusDateDto> dataList;


}

