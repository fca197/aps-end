package com.olivia.peanut.aps.api.entity.apsSchedulingVersionLimit;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * (ApsSchedulingVersionLimit)查询对象返回
 *
 * @author peanut
 * @since 2024-04-19 14:57:00
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingVersionLimitQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<ApsSchedulingVersionLimitDto> dataList;


}

