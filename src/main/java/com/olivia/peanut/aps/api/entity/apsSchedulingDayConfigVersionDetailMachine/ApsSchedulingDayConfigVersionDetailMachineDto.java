package com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersionDetailMachine;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 排程版本详情_机器(ApsSchedulingDayConfigVersionDetailMachine)查询对象返回
 *
 * @author makejava
 * @since 2024-10-27 00:12:55
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingDayConfigVersionDetailMachineDto extends BaseEntityDto {

  /***
   *  版本ID
   */
  @NotNull(message = "版本ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long schedulingDayId;
  /***
   *  订单ID
   */
  @NotNull(message = "订单ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long orderId;
  private String orderNo;
  private String orderUserName;
  /***
   *  机器ID
   */
  @NotNull(message = "机器ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long machineId;
  private String machineName;
  /***
   *  状态ID
   */
  @NotNull(message = "状态ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long statusId;

  /***
   *  开始时间
   */
  @NotNull(message = "开始时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private LocalDateTime beginDateTime;
  /***
   *  结束时间
   */
  @NotNull(message = "结束时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private LocalDateTime endDateTime;


  private Long startSecond;
  private Long endSecond;
  private Long useTime;
}


