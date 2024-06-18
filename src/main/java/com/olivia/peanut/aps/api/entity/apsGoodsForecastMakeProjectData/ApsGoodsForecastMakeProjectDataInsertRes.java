package com.olivia.peanut.aps.api.entity.apsGoodsForecastMakeProjectData;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecastMakeProjectData)保存返回
 *
 * @author peanut
 * @since 2024-05-10 13:58:07
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastMakeProjectDataInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

