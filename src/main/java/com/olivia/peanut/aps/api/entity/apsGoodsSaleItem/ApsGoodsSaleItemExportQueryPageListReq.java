package com.olivia.peanut.aps.api.entity.apsGoodsSaleItem;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsSaleItem)查询对象入参
 *
 * @author peanut
 * @since 2024-03-30 10:38:36
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsSaleItemExportQueryPageListReq {

  private int pageNum;
  private int pageSize;
  private Boolean queryPage = true;
  private ApsGoodsSaleItemDto data;


  public void checkParam() {
    this.pageNum = 1;
    this.pageSize = 9999;
  }

}

