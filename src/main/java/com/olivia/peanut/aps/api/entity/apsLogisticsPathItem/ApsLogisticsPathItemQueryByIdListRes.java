package com.olivia.peanut.aps.api.entity.apsLogisticsPathItem;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 物流路详情径表(ApsLogisticsPathItem)查询对象返回
 *
 * @author peanut
 * @since 2024-07-18 13:27:41
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsLogisticsPathItemQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<ApsLogisticsPathItemDto> dataList;


}

