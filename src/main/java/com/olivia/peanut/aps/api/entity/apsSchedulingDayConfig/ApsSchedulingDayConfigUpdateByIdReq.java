package com.olivia.peanut.aps.api.entity.apsSchedulingDayConfig;

import java.time.LocalDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 排程版本表(ApsSchedulingDayConfig)表实体类
 *
 * @author peanut
 * @since 2024-07-19 19:19:49
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingDayConfigUpdateByIdReq extends ApsSchedulingDayConfigDto {


  public void checkParam() {
  }

}

