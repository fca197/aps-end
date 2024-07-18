package com.olivia.peanut.aps.api.entity.apsLogisticsPathItem;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 物流路详情径表(ApsLogisticsPathItem)查询对象返回
 *
 * @author peanut
 * @since 2024-07-18 13:27:40
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsLogisticsPathItemQueryListRes {

  /***
   * 返回对象列表
   */
  private List<ApsLogisticsPathItemDto> dataList;


}

