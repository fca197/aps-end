package com.olivia.peanut.aps.api.entity.apsGoodsForecastComputeSaleData;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import lombok.Getter;
import lombok.Setter;

/**
 * (ApsGoodsForecastComputeSaleData)查询对象返回
 *
 * @author peanut
 * @since 2024-03-31 20:58:35
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastComputeSaleDataDto extends BaseEntityDto {

  private Long forecastId;
  private Long saleConfigId;
  private String saleConfigCode;
  private Integer year;

  private Long month01;
  private Long month02;
  private Long month03;
  private Long month04;
  private Long month05;
  private Long month06;
  private Long month07;
  private Long month08;
  private Long month09;
  private Long month10;
  private Long month11;
  private Long month12;

}

