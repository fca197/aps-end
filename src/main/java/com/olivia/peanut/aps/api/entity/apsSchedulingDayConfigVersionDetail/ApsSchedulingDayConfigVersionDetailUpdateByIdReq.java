package com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersionDetail;

import java.time.LocalDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 排程版本配置明细表(ApsSchedulingDayConfigVersionDetail)表实体类
 *
 * @author peanut
 * @since 2024-07-19 15:05:09
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingDayConfigVersionDetailUpdateByIdReq extends ApsSchedulingDayConfigVersionDetailDto {


  public void checkParam() {
  }

}

