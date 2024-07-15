package com.olivia.peanut.aps.api.entity.apsRollingForecastOrder;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 滚动预测(ApsRollingForecastOrder)查询对象返回
 *
 * @author peanut
 * @since 2024-07-14 20:22:28
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsRollingForecastOrderQueryListRes {

  /***
   * 返回对象列表
   */
  private List<ApsRollingForecastOrderDto> dataList;


}

