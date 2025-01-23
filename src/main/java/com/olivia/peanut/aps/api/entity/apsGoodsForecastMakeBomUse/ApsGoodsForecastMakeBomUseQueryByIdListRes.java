package com.olivia.peanut.aps.api.entity.apsGoodsForecastMakeBomUse;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * (ApsGoodsForecastMakeBomUse)查询对象返回
 *
 * @author peanut
 * @since 2024-05-15 10:26:04
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastMakeBomUseQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<ApsGoodsForecastMakeBomUseDto> dataList;


}

