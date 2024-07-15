package com.olivia.peanut.aps.api.entity.apsRollingForecastFactoryCapacity;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 滚动预测(ApsRollingForecastFactoryCapacity)查询对象返回
 *
 * @author peanut
 * @since 2024-07-14 20:22:23
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsRollingForecastFactoryCapacityQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<ApsRollingForecastFactoryCapacityDto> dataList;


}

