package com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersion;

import java.time.LocalDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 排程版本(ApsSchedulingDayConfigVersion)表实体类
 *
 * @author peanut
 * @since 2024-07-19 19:19:55
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingDayConfigVersionUpdateByIdReq extends ApsSchedulingDayConfigVersionDto {


  public void checkParam() {
  }

}

