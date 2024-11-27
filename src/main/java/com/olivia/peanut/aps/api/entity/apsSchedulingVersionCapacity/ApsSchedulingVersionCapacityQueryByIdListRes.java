package com.olivia.peanut.aps.api.entity.apsSchedulingVersionCapacity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * (ApsSchedulingVersionCapacity)查询对象返回
 *
 * @author peanut
 * @since 2024-04-18 14:57:35
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingVersionCapacityQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<ApsSchedulingVersionCapacityDto> dataList;


}

