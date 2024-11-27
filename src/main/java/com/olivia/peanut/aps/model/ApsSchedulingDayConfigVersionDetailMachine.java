package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 排程版本详情_机器(ApsSchedulingDayConfigVersionDetailMachine)表实体类
 *
 * @author makejava
 * @since 2024-10-27 00:12:55
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_scheduling_day_config_version_detail_machine")
public class ApsSchedulingDayConfigVersionDetailMachine extends BaseEntity {
  /***
   *  版本ID
   */
  private Long schedulingDayId;
  /***
   *  订单ID
   */
  private Long orderId;
  /***
   *  机器ID
   */
  private Long machineId;
  /***
   *  状态ID
   */
  private Long statusId;
  /***
   *  开始时间
   */
  private LocalDateTime beginDateTime;
  /***
   *  结束时间
   */
  private LocalDateTime endDateTime;

  private Long startSecond;
  private Long endSecond;
  private Long useTime;
}

