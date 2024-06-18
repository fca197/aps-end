package com.olivia.peanut.aps.api.entity.apsOrderGoodsStatusDate;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 订单商品状态表(ApsOrderGoodsStatusDate)查询对象返回
 *
 * @author peanut
 * @since 2024-06-14 10:26:59
 */
//@Accessors(chain=true)
@Getter
@Setter
@Accessors(chain = true)
@SuppressWarnings("serial")
public class ApsOrderGoodsStatusDateDto extends BaseEntityDto {

  @NotNull(message = "商品不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long orderId;
  /***
   *  商品ID
   */
  @NotNull(message = "商品不能为空", groups = {InsertCheck.class, UpdateCheck.class})
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
//  @NotNull(message = "期望制造时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private LocalDate expectMakeTime;
  /***
   *  实际制造时间
   */
  @NotNull(message = "实际制造时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private LocalDate actualMakeTime;
  /***
   *  工厂ID
   */
  @NotNull(message = "工厂不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long factoryId;


  private Integer statusIndex;
}


