package com.olivia.peanut.aps.api.entity.apsGoodsForecast;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * (ApsGoodsForecast)保存返回
 *
 * @author peanut
 * @since 2024-03-30 13:38:53
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastImportRes {

  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

