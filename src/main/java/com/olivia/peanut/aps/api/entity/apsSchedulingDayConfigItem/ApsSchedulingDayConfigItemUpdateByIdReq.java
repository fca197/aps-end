package com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigItem;

import java.time.LocalDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 排程版本配置表(ApsSchedulingDayConfigItem)表实体类
 *
 * @author peanut
 * @since 2024-07-19 15:05:04
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingDayConfigItemUpdateByIdReq extends ApsSchedulingDayConfigItemDto {


  public void checkParam() {
  }

}

