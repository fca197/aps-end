package com.olivia.peanut.aps.api.entity.apsSchedulingVersionDay;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsSchedulingVersionDay)查询对象入参
 *
 * @author peanut
 * @since 2024-04-23 14:37:05
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingVersionDayQueryListReq {

  private ApsSchedulingVersionDayDto data;
}

