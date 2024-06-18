package com.olivia.peanut.aps.api.entity.apsGoodsForecast;

import com.alibaba.fastjson2.JSON;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import com.olivia.sdk.utils.DateUtils;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * (ApsGoodsForecast)查询对象返回
 *
 * @author peanut
 * @since 2024-03-30 13:38:53
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastDto extends BaseEntityDto {

  @NotNull(message = "零件不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long goodsId;
  @NotNull(message = "预测编号不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String forecastNo;
  @NotNull(message = "预测名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String forecastName;
  @NotNull(message = "预测开始时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String forecastBeginDate;
  @NotNull(message = "预测结束时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String forecastEndDate;

  private String months;
  private Integer forecastStatus;


  public void buildMonthList() {

    this.setMonths(JSON.toJSONString(DateUtils.getMonthList(this.getForecastBeginDate(), this.getForecastEndDate())));
  }
}


