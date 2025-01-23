package com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersionDetail;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 排程版本配置明细表(ApsSchedulingDayConfigVersionDetail)查询对象返回
 *
 * @author peanut
 * @since 2024-07-19 19:19:58
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingDayConfigVersionDetailDto extends BaseEntityDto {

  //  @NotNull(message = "${column.comment}不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long schedulingDayId;
  /***
   *  配置类型 sale,part,bom
   */
  @NotBlank(message = "配置类型 sale,part,bom不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String configBizType;
  /***
   *  配置业务ID
   */
  @NotNull(message = "配置业务ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long configBizId;
  /***
   *  配置业务名称
   */
  @NotBlank(message = "配置业务名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String configBizName;
  /***
   *  配置业务数量
   */
  @NotNull(message = "配置业务数量不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long configBizNum;
  /***
   *  订单ID
   */
  @NotNull(message = "订单ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long orderId;
  /***
   *  订单编号
   */
  @NotBlank(message = "订单编号不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String orderNo;
  /***
   *  是否匹配
   */
  @NotNull(message = "是否匹配不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Boolean isMatch;
  /***
   *  循环次数
   */
  @NotNull(message = "循环次数不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Integer loopIndex;
  /***
   *  是否满足
   */
  @NotNull(message = "是否满足不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Boolean loopEnough;

  private Long roomId;
  private Long statusId;
}


