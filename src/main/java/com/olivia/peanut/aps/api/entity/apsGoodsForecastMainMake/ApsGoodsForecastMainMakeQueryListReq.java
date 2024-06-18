package com.olivia.peanut.aps.api.entity.apsGoodsForecastMainMake;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecastMainMake)查询对象入参
 *
 * @author peanut
 * @since 2024-04-08 09:52:49
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastMainMakeQueryListReq {

  private ApsGoodsForecastMainMakeDto data;
}

