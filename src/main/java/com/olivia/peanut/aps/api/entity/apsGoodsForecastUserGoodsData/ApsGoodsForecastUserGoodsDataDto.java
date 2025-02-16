package com.olivia.peanut.aps.api.entity.apsGoodsForecastUserGoodsData;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import lombok.Getter;
import lombok.Setter;


/**
 * (ApsGoodsForecastUserGoodsData)查询对象返回
 *
 * @author peanut
 * @since 2024-03-30 18:29:07
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastUserGoodsDataDto extends BaseEntityDto {

  private Long forecastId;
  private Integer year;
  private Integer month_01;
  private Integer month_02;
  private Integer month_03;
  private Integer month_04;
  private Integer month_05;
  private Integer month_06;
  private Integer month_07;
  private Integer month_08;
  private Integer month_09;
  private Integer month_10;
  private Integer month_11;
  private Integer month_12;
}


