package com.olivia.peanut.aps.api.entity.apsSellerStore;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * aps销售门店(ApsSellerStore)查询对象返回
 *
 * @author makejava
 * @since 2024-11-15 14:58:58
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSellerStoreQueryListRes {
  /***
   * 返回对象列表
   */
  private List<ApsSellerStoreDto> dataList;


}

