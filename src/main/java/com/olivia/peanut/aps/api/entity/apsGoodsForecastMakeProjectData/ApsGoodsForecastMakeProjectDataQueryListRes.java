package com.olivia.peanut.aps.api.entity.apsGoodsForecastMakeProjectData;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecastMakeProjectData)查询对象返回
 *
 * @author peanut
 * @since 2024-05-10 13:58:07
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastMakeProjectDataQueryListRes {

  /***
   * 返回对象列表
   */
  private List<ApsGoodsForecastMakeProjectDataDto> dataList;


}

