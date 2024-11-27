package com.olivia.peanut.aps.api.entity.apsGoodsForecastMainMake;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * (ApsGoodsForecastMainMake)查询对象返回
 *
 * @author peanut
 * @since 2024-04-08 09:52:49
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastMainMakeQueryListRes {

  /***
   * 返回对象列表
   */
  private List<ApsGoodsForecastMainMakeDto> dataList;


}

