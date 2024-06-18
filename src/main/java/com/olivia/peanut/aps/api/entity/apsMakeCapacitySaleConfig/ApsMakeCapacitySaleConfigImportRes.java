package com.olivia.peanut.aps.api.entity.apsMakeCapacitySaleConfig;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsMakeCapacitySaleConfig)保存返回
 *
 * @author peanut
 * @since 2024-04-13 12:05:06
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsMakeCapacitySaleConfigImportRes {

  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

