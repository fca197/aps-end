package com.olivia.peanut.aps.api.entity.apsSchedulingIssueItem;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 排产下发详情(ApsSchedulingIssueItem)查询对象返回
 *
 * @author peanut
 * @since 2024-07-20 13:55:56
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingIssueItemDto extends BaseEntityDto {


  @NotNull(message = "排产id不能为空", groups = {UpdateCheck.class})
  private Long schedulingVersionId;
  /***
   *  当前日期
   */
//  @NotBlank(message = "当前日期不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String currentDay;

  /***
   *  订单ID
   */
//  @NotNull(message = "订单ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long orderId;
  /***
   *  商品ID
   */
//  @NotNull(message = "商品ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long goodsId;
  /***
   *  生产序号
   */
//  @NotNull(message = "生产序号不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long numberIndex;
  /***
   *  工厂id
   */
//  @NotNull(message = "工厂id不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long factoryId;

}


