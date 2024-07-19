package com.olivia.peanut.aps.api.entity.apsSchedulingDayConfig;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 排程版本表(ApsSchedulingDayConfig)保存入参
 *
 * @author peanut
 * @since 2024-07-19 15:05:00
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingDayConfigInsertReq extends ApsSchedulingDayConfigDto {

  public void checkParam() {
  }
}

