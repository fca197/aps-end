package com.olivia.peanut.aps.api.entity.apsOrderRollingForecast;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 滚动预测(ApsOrderRollingForecast)查询对象返回
 *
 * @author peanut
 * @since 2024-07-14 19:43:52
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderRollingForecastDto extends BaseEntityDto {

  /***
   *  唯一编码
   */
  @NotBlank(message = "唯一编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String rollCode;
  /***
   *  名称
   */
  @NotBlank(message = "名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String rollName;
  /***
   *  开始时间
   */
  @NotNull(message = "开始时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private LocalDate beginTime;
  /***
   *  结束时间
   */
  @NotNull(message = "结束时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private LocalDate endTime;

}


