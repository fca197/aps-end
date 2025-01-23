package com.olivia.peanut.aps.api.entity.apsSellerStore;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * aps销售门店(ApsSellerStore)根据ID删除多个反参
 *
 * @author makejava
 * @since 2024-11-15 14:58:58
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSellerStoreDeleteByIdListRes {
  /***
   * 受影响行数
   */
  private int count;

}

