package com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersion;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 排程版本(ApsSchedulingDayConfigVersion)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-07-19 15:05:06
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingDayConfigVersionDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

