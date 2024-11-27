package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 排程结果机器使用率(ApsSchedulingDayConfigVersionDetailMachineUseTime)表实体类
 *
 * @author makejava
 * @since 2024-11-11 15:21:49
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_scheduling_day_config_version_detail_machine_use_time")
public class ApsSchedulingDayConfigVersionDetailMachineUseTime extends BaseEntity {
  /***
   *  排程ID
   */
  private Long schedulingDayId;
  /***
   *  机器ID
   */
  private Long machineId;
  /***
   *  耗时
   */
  private Long useTime;
  /***
   *  使用率
   */
  private BigDecimal useUsageRate;
  /***
   *  商品数
   */
  private Integer makeProduceCount;

}

