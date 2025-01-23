package com.olivia.peanut.aps.api.entity.apsRollingForecastOrder;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 滚动预测(ApsRollingForecastOrder)查询对象入参
 *
 * @author peanut
 * @since 2024-07-14 20:22:28
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsRollingForecastOrderQueryByIdListReq {

  private List<Long> idList;


}

