package com.olivia.peanut.aps.api.entity.apsGoodsForecastMake;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * (ApsGoodsForecastMake)查询对象返回
 *
 * @author peanut
 * @since 2024-04-07 15:07:49
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastMakeQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<ApsGoodsForecastMakeDto> dataList;


}

