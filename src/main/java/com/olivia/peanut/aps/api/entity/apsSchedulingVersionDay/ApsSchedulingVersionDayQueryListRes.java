package com.olivia.peanut.aps.api.entity.apsSchedulingVersionDay;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsSchedulingVersionDay)查询对象返回
 *
 * @author peanut
 * @since 2024-04-23 14:37:05
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingVersionDayQueryListRes {

  /***
   * 返回对象列表
   */
  private List<ApsSchedulingVersionDayDto> dataList;


}

