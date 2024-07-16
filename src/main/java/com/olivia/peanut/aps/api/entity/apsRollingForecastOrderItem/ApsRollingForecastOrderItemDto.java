package com.olivia.peanut.aps.api.entity.apsRollingForecastOrderItem;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 * 滚动预测订单节点表(ApsRollingForecastOrderItem)查询对象返回
 *
 * @author peanut
 * @since 2024-07-16 10:31:20
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsRollingForecastOrderItemDto extends BaseEntityDto {

  /***
   *  预测ID
   */
  @NotNull(message = "预测ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long forecastId;
  /***
   *  工厂ID
   */
  @NotNull(message = "工厂ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long factoryId;
  /***
   *  订单ID
   */
  @NotNull(message = "订单ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long orderId;
  /***
   *  状态ID
   */
  @NotNull(message = "状态ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long goodsStatusId;
  /***
   *  状态开始时间
   */
  @NotNull(message = "状态开始时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private LocalDate statusBeginDate;

}


