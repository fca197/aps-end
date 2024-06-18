package com.olivia.peanut.aps.api.entity.apsSchedulingVersionLimit;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsSchedulingVersionLimit)查询对象返回
 *
 * @author peanut
 * @since 2024-04-19 14:56:59
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingVersionLimitQueryListRes {

  /***
   * 返回对象列表
   */
  private List<ApsSchedulingVersionLimitDto> dataList;


}

