package com.olivia.peanut.aps.api.entity.apsRollingForecastOrder;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 * 滚动预测(ApsRollingForecastOrder)查询对象返回
 *
 * @author peanut
 * @since 2024-07-14 20:22:29
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsRollingForecastOrderDto extends BaseEntityDto {

  @NotNull(message = "工厂id不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long factoryId;
  private String factoryName;
  @NotNull(message = "开始状态id不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long beginStatusId;
  private String beginStatusName;
  @NotNull(message = "结束状态id不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long endStatusId;
  private String endStatusName;
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


