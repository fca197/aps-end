package com.olivia.peanut.aps.api.entity.apsBomSupplier;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 供应商表(ApsBomSupplier)查询对象入参
 *
 * @author makejava
 * @since 2024-12-15 14:39:46
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsBomSupplierQueryByIdListReq {
  private List<Long> idList;

}

