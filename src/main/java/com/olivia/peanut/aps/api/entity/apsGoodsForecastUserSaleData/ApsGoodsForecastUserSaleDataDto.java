package com.olivia.peanut.aps.api.entity.apsGoodsForecastUserSaleData;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * (ApsGoodsForecastUserSaleData)查询对象返回
 *
 * @author peanut
 * @since 2024-03-30 18:29:07
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastUserSaleDataDto extends BaseEntityDto {

  private Long forecastId;
  private Long saleConfigId;
  private Integer year;
  private BigDecimal month_01;
  private BigDecimal month_02;
  private BigDecimal month_03;
  private BigDecimal month_04;
  private BigDecimal month_05;
  private BigDecimal month_06;
  private BigDecimal month_07;
  private BigDecimal month_08;
  private BigDecimal month_09;
  private BigDecimal month_10;
  private BigDecimal month_11;
  private BigDecimal month_12;

}


