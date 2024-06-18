package com.olivia.peanut.aps.api.entity.apsGoodsForecastMainMakeSaleData;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecastMainMakeSaleData)查询对象返回
 *
 * @author peanut
 * @since 2024-04-08 09:52:50
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastMainMakeSaleDataQueryListRes {

  /***
   * 返回对象列表
   */
  private List<ApsGoodsForecastMainMakeSaleDataDto> dataList;


}

