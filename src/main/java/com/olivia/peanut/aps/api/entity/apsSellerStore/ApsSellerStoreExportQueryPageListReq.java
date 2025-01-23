package com.olivia.peanut.aps.api.entity.apsSellerStore;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * aps销售门店(ApsSellerStore)查询对象入参
 *
 * @author makejava
 * @since 2024-11-15 14:58:58
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSellerStoreExportQueryPageListReq {
  private int pageNum;
  private int pageSize;
  private Boolean queryPage = true;
  private ApsSellerStoreDto data;


}

