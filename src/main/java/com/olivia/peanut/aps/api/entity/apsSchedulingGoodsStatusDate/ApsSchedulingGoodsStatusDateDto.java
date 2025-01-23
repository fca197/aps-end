package com.olivia.peanut.aps.api.entity.apsSchedulingGoodsStatusDate;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 订单商品状态表(ApsSchedulingGoodsStatusDate)查询对象返回
 *
 * @author peanut
 * @since 2024-06-14 21:35:10
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingGoodsStatusDateDto extends BaseEntityDto {

  /***
   *  排产ID
   */
  @NotNull(message = "排产ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long schedulingId;
  /***
   *  订单ID
   */
  @NotNull(message = "订单ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long orderId;
  /***
   *  商品ID
   */
  @NotNull(message = "商品ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long goodsId;
  /***
   *  订单状态
   */
  @NotNull(message = "订单状态不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long goodsStatusId;
  /***
   *  期望制造时间
   */
  @NotNull(message = "期望开始制造时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private LocalDate expectMakeBeginTime;

  @NotNull(message = "期望结束制造时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private LocalDate expectMakeEndTime;
  /***
   *  实际制造时间
   */
  @NotNull(message = "实际开始制造时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private LocalDateTime actualMakeBeginTime;
  @NotNull(message = "实际结束制造时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private LocalDateTime actualMakeEndTime;
  /***
   *  工厂ID
   */
  @NotNull(message = "工厂ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long factoryId;
  /***
   *  状态索引
   */
  @NotNull(message = "状态索引不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Integer statusIndex;

}


