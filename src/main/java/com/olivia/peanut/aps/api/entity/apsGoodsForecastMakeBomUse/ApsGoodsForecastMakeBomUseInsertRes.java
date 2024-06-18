package com.olivia.peanut.aps.api.entity.apsGoodsForecastMakeBomUse;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecastMakeBomUse)保存返回
 *
 * @author peanut
 * @since 2024-05-15 10:26:03
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastMakeBomUseInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

