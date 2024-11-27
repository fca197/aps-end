package com.olivia.peanut.aps.api.entity.apsSellerStore;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * aps销售门店(ApsSellerStore)查询对象入参
 *
 * @author makejava
 * @since 2024-11-15 14:58:59
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSellerStoreQueryByIdListReq {
  private List<Long> idList;


}

