package com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigItem;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 排程版本配置表(ApsSchedulingDayConfigItem)查询对象入参
 *
 * @author peanut
 * @since 2024-07-19 15:05:04
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingDayConfigItemExportQueryPageListReq {

  private int pageNum;
  private int pageSize;
  private Boolean queryPage = true;
  private ApsSchedulingDayConfigItemDto data;


  public void checkParam() {
  }

}

