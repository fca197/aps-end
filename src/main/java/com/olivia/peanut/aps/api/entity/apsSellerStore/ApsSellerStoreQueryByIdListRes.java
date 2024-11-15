package com.olivia.peanut.aps.api.entity.apsSellerStore;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * aps销售门店(ApsSellerStore)查询对象返回
 *
 * @author makejava
 * @since 2024-11-15 14:58:59
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSellerStoreQueryByIdListRes {
  /***
   * 返回对象列表
   */
  private List<ApsSellerStoreDto> dataList;


}

