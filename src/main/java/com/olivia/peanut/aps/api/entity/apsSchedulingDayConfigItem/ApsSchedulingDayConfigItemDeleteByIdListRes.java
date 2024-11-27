package com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigItem;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 排程版本配置表(ApsSchedulingDayConfigItem)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-07-19 19:19:51
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingDayConfigItemDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

