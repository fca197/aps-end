package com.olivia.peanut.aps.api.entity.apsSellerStore;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * aps销售门店(ApsSellerStore)表实体类
 *
 * @author makejava
 * @since 2024-11-15 14:58:58
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSellerStoreUpdateByIdReq extends ApsSellerStoreDto {


  public void checkParam() {
  }

}

