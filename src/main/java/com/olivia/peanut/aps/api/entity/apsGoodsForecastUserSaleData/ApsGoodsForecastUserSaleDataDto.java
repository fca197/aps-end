package com.olivia.peanut.aps.api.entity.apsGoodsForecastUserSaleData;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import lombok.Getter;
import lombok.Setter;

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
  private Double month_01;
  private Double month_02;
  private Double month_03;
  private Double month_04;
  private Double month_05;
  private Double month_06;
  private Double month_07;
  private Double month_08;
  private Double month_09;
  private Double month_10;
  private Double month_11;
  private Double month_12;

}


