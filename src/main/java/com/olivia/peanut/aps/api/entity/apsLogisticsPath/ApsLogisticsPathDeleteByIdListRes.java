package com.olivia.peanut.aps.api.entity.apsLogisticsPath;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 物流路径表(ApsLogisticsPath)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-07-18 13:27:36
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsLogisticsPathDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

