package com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersionDetail;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 排程版本配置明细表(ApsSchedulingDayConfigVersionDetail)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-07-19 19:19:57
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingDayConfigVersionDetailDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

