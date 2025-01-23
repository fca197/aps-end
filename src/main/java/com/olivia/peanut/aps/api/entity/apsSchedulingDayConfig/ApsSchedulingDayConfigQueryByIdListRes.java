package com.olivia.peanut.aps.api.entity.apsSchedulingDayConfig;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 排程版本表(ApsSchedulingDayConfig)查询对象返回
 *
 * @author peanut
 * @since 2024-07-19 19:19:50
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingDayConfigQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<ApsSchedulingDayConfigDto> dataList;


}

