package com.olivia.peanut.aps.api.entity.apsGoodsForecastMainMake;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * (ApsGoodsForecastMainMake)查询对象返回
 *
 * @author peanut
 * @since 2024-04-08 09:52:50
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastMainMakeDto extends BaseEntityDto {

  @NotNull(message = "零件不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long goodsId;
  private String goodsName;
  @NotBlank(message = "预测单不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String forecastMakeMainNo;
  @NotBlank(message = "预测单名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String forecastMakeMainName;
  @NotBlank(message = "开始日期不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String forecastMakeMainBeginDate;
  @NotBlank(message = "结束日期不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String forecastMakeMainEndDate;
  @NotNull(message = "工厂不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long factoryId;

}


