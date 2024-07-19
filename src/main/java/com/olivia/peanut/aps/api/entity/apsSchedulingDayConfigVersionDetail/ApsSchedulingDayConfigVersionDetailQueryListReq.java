package com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersionDetail;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 排程版本配置明细表(ApsSchedulingDayConfigVersionDetail)查询对象入参
 *
 * @author peanut
 * @since 2024-07-19 19:19:57
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingDayConfigVersionDetailQueryListReq {

  private ApsSchedulingDayConfigVersionDetailDto data;
}

