package com.olivia.peanut.aps.api.entity.apsOrderGoodsForecastMake;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 订单商品节点预测表(ApsOrderGoodsForecastMake)查询对象入参
 *
 * @author peanut
 * @since 2024-06-02 23:11:41
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderGoodsForecastMakeExportQueryPageListReq {

  private int pageNum;
  private int pageSize;
  private Boolean queryPage = true;
  private ApsOrderGoodsForecastMakeDto data;


}

