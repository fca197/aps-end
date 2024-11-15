package com.olivia.peanut.aps.api.entity.apsSellerStore;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.time.LocalDate;
import java.time.LocalDateTime;

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
public class ApsSellerStoreQueryListReq {

  private ApsSellerStoreDto data;
}

