package com.olivia.peanut.aps.api.entity.apsOrderGoodsStatusDate;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 订单商品状态表(ApsOrderGoodsStatusDate)查询对象入参
 *
 * @author peanut
 * @since 2024-06-14 10:26:59
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderGoodsStatusDateExportQueryPageListReq {

  private int pageNum;
  private int pageSize;
  private Boolean queryPage = true;
  private ApsOrderGoodsStatusDateDto data;


  public void checkParam() {
  }

}

