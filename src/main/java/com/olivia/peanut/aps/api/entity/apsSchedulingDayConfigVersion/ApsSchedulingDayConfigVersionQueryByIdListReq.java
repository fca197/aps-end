package com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersion;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 排程版本(ApsSchedulingDayConfigVersion)查询对象入参
 *
 * @author peanut
 * @since 2024-07-19 15:05:07
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingDayConfigVersionQueryByIdListReq {

  private List<Long> idList;


  public void checkParam() {
  }

}

