package com.olivia.peanut.aps.api.entity.apsLogisticsPath;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 物流路径表(ApsLogisticsPath)保存返回
 *
 * @author peanut
 * @since 2024-07-18 13:27:36
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsLogisticsPathInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}
