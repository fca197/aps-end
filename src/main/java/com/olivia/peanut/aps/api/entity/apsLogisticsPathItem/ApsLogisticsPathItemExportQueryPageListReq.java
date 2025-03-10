package com.olivia.peanut.aps.api.entity.apsLogisticsPathItem;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 物流路详情径表(ApsLogisticsPathItem)查询对象入参
 *
 * @author peanut
 * @since 2024-07-18 13:27:40
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsLogisticsPathItemExportQueryPageListReq {

  private int pageNum;
  private int pageSize;
  private Boolean queryPage = true;
  private ApsLogisticsPathItemDto data;


}

