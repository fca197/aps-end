package com.olivia.peanut.aps.api.entity.apsGoodsForecastMain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecastMain)查询对象入参
 *
 * @author peanut
 * @since 2024-04-02 09:42:27
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastMainQueryListReq {

  private ApsGoodsForecastMainDto data;
}

