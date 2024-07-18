package com.olivia.peanut.aps.api.entity.apsLogisticsPathItem;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 物流路详情径表(ApsLogisticsPathItem)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-07-18 13:27:40
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsLogisticsPathItemDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

