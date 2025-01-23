package com.olivia.peanut.aps.api.entity.apsOrderGoodsStatusDate;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 订单商品状态表(ApsOrderGoodsStatusDate)查询对象返回
 *
 * @author peanut
 * @since 2024-07-05 13:44:45
 */
//@Accessors(chain=true)
@Getter
@Setter
@Accessors(chain = true)
@SuppressWarnings("serial")
public class ApsOrderGoodsStatusDateDto extends BaseEntityDto {

  //  @NotNull(message = "${column.comment}不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long orderId;
  /***
   *  商品ID
   */
  @NotNull(message = "商品ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long goodsId;
  private String goodsName;
  /***
   *  订单状态
   */
  @NotNull(message = "订单状态不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long goodsStatusId;
  private String goodsStatusName;
  /***
   *  期望制造时间
   */
  @NotNull(message = "期望制造时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private LocalDate expectMakeTime;
  /***
   *  实际制造时间
   */
  @NotNull(message = "实际制造时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private LocalDate actualMakeTime;
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
  /***
   *  预计开始时间
   */
  @NotNull(message = "预计开始时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private LocalDate expectMakeBeginTime;
  /***
   *  预计结束时间
   */
  @NotNull(message = "预计结束时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private LocalDate expectMakeEndTime;
  /***
   *  实际开始时间
   */
  @NotNull(message = "实际开始时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private LocalDateTime actualMakeBeginTime;
  /***
   *  实际结束时间
   */
  @NotNull(message = "实际结束时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private LocalDate actualMakeEndTime;

}


