package com.olivia.peanut.aps.api.entity.apsGoodsForecastMain;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import lombok.Getter;
import lombok.Setter;

/**
 * (ApsGoodsForecastMain)查询对象返回
 *
 * @author peanut
 * @since 2024-04-02 09:42:27
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastMainDto extends BaseEntityDto {

  private Long factoryId;
  private Long goodsId;
  private String factoryName;
  private String goodsName;
  private String forecastNo;
  private String forecastName;
  private String forecastBeginDate;
  private String forecastEndDate;
  private String month;
  private String months;
}


