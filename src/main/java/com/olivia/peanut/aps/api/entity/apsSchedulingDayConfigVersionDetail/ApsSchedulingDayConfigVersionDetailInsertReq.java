package com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersionDetail;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 排程版本配置明细表(ApsSchedulingDayConfigVersionDetail)保存入参
 *
 * @author peanut
 * @since 2024-07-19 15:05:08
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingDayConfigVersionDetailInsertReq extends ApsSchedulingDayConfigVersionDetailDto {

  public void checkParam() {
  }
}

