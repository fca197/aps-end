package com.olivia.peanut.aps.api.entity.apsGoodsForecastMakeBomUse;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

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
public class ApsGoodsForecastMakeBomUseImportRes {

  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

