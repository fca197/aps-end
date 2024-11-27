package com.olivia.peanut.aps.api.entity.apsGoodsForecastMakeProjectData;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * (ApsGoodsForecastMakeProjectData)查询对象返回
 *
 * @author peanut
 * @since 2024-05-10 13:58:12
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastMakeProjectDataQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<ApsGoodsForecastMakeProjectDataDto> dataList;


}

