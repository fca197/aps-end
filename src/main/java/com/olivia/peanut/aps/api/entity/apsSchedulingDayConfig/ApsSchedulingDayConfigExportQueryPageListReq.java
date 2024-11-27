package com.olivia.peanut.aps.api.entity.apsSchedulingDayConfig;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 排程版本表(ApsSchedulingDayConfig)查询对象入参
 *
 * @author peanut
 * @since 2024-07-19 19:19:49
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingDayConfigExportQueryPageListReq {

  private int pageNum;
  private int pageSize;
  private Boolean queryPage = true;
  private ApsSchedulingDayConfigDto data;


}

