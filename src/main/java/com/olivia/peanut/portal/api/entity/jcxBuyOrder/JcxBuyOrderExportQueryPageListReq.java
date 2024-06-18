package com.olivia.peanut.portal.api.entity.jcxBuyOrder;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (JcxBuyOrder)查询对象入参
 *
 * @author peanut
 * @since 2024-03-27 13:51:36
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class JcxBuyOrderExportQueryPageListReq {

  private int pageNum;
  private int pageSize;
  private Boolean queryPage = true;
  private JcxBuyOrderDto data;


  public void checkParam() {
  }

}

