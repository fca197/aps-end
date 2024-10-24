package com.olivia.peanut.aps.api.entity.apsRollingForecastFactoryCapacity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 滚动预测(ApsRollingForecastFactoryCapacity)保存返回
 *
 * @author peanut
 * @since 2024-07-14 20:22:23
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsRollingForecastFactoryCapacityImportRes {

  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

