package com.olivia.peanut.aps.api.entity.apsOrderGoodsBom;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

import com.alibaba.excel.annotation.ExcelProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 订单商品零件表(ApsOrderGoodsBom)查询对象返回
 *
 * @author peanut
 * @since 2024-07-30 10:28:23
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderGoodsBomImportReq extends ApsOrderGoodsBomDto {


  public void checkParam() {
  }

}


