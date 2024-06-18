package com.olivia.peanut.aps.api.entity.apsGoodsForecastMainSaleData;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import lombok.Getter;
import lombok.Setter;

/**
 * (ApsGoodsForecastMainSaleData)查询对象返回
 *
 * @author peanut
 * @since 2024-04-02 09:42:28
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastMainSaleDataDto extends BaseEntityDto {

  private Integer goodsId;
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


