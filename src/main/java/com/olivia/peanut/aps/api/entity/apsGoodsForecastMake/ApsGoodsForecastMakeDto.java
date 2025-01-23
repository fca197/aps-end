package com.olivia.peanut.aps.api.entity.apsGoodsForecastMake;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * (ApsGoodsForecastMake)查询对象返回
 *
 * @author peanut
 * @since 2024-04-07 15:07:49
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastMakeDto extends BaseEntityDto {

  @NotNull(message = "预测主表不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long forecastMainId;
  //  @NotNull(message = "商品不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long goodsId;
  private String goodsName;
  @NotBlank(message = "预测编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String forecastMakeMonthNo;
  @NotBlank(message = "预测名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String forecastMakeMonthName;
  @NotBlank(message = "预测开始时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String forecastMakeMonthBeginDate;
  @NotBlank(message = "预测结束时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String forecastMakeMonthEndDate;
  //  @NotNull(message = "工厂不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long factoryId;
  private String month;
  private String weeks;

  private Boolean isDeploy;
}


