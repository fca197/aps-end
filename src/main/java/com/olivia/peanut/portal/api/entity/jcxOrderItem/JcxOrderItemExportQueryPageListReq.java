package com.olivia.peanut.portal.api.entity.jcxOrderItem;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (JcxOrderItem)查询对象入参
 *
 * @author peanut
 * @since 2024-03-22 10:38:07
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class JcxOrderItemExportQueryPageListReq {

  private int pageNum;
  private int pageSize;
  private Boolean queryPage = true;
  private JcxOrderItemDto data;


  public void checkParam() {
  }

}

