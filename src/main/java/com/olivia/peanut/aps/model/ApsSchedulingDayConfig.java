package com.olivia.peanut.aps.model;


import java.time.LocalDate;
import java.time.LocalDateTime;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 排程版本表(ApsSchedulingDayConfig)表实体类
 *
 * @author peanut
 * @since 2024-07-19 15:05:00
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_scheduling_day_config")
public class ApsSchedulingDayConfig extends BaseEntity {

  /***
   *  工厂ID
   */
  private Long factoryId;
  private String schedulingDayNo;
  private String schedulingDayName;
  /***
   *  是否默认 0 否,1 是
   */
  private Boolean isDefault;

}

