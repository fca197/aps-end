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
  private Long month_01;
  private Long month_02;
  private Long month_03;
  private Long month_04;
  private Long month_05;
  private Long month_06;
  private Long month_07;
  private Long month_08;
  private Long month_09;
  private Long month_10;
  private Long month_11;
  private Long month_12;
}


