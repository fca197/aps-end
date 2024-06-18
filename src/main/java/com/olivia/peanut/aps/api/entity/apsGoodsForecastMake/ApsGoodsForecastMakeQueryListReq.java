package com.olivia.peanut.aps.api.entity.apsGoodsForecastMake;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecastMake)查询对象入参
 *
 * @author peanut
 * @since 2024-04-07 15:07:48
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastMakeQueryListReq {

  private ApsGoodsForecastMakeDto data;
}

