package com.olivia.peanut.aps.api.entity.apsSchedulingGoodsStatusDate;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 订单商品状态表(ApsSchedulingGoodsStatusDate)查询对象入参
 *
 * @author peanut
 * @since 2024-06-14 21:35:09
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingGoodsStatusDateExportQueryPageListReq {

  private int pageNum;
  private int pageSize;
  private Boolean queryPage = true;
  private ApsSchedulingGoodsStatusDateDto data;


}

