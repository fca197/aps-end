package com.olivia.peanut.aps.api.entity.apsGoodsForecastMainGoodsData;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import lombok.Getter;
import lombok.Setter;

/**
 * (ApsGoodsForecastMainGoodsData)查询对象返回
 *
 * @author peanut
 * @since 2024-04-02 13:44:29
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastMainGoodsDataDto extends BaseEntityDto {

  private Long goodsId;
  private Long year;
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
  private Long forecastMainId;

}


