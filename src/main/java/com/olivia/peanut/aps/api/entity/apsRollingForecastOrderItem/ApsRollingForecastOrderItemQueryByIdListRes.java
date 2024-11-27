package com.olivia.peanut.aps.api.entity.apsRollingForecastOrderItem;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 滚动预测订单节点表(ApsRollingForecastOrderItem)查询对象返回
 *
 * @author peanut
 * @since 2024-07-16 10:31:20
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsRollingForecastOrderItemQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<ApsRollingForecastOrderItemDto> dataList;


}

