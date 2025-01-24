package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.peanut.aps.enums.ApsSchedulingDayConfigVersionProductType;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * 排程版本(ApsSchedulingDayConfigVersion)表实体类
 *
 * @author peanut
 * @since 2024-07-19 19:19:55
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_scheduling_day_config_version")
public class ApsSchedulingDayConfigVersion extends BaseEntity {

  /***
   *  工厂ID
   */
  private Long factoryId;

  private Long processId;
  /***
   *  排程版本号
   */
  private String schedulingDayVersionNo;
  /***
   *  排程日期
   */
  private LocalDate schedulingDay;
  /***
   *  是否下发第三方
   */
  private Boolean isIssuedThird;

  private String headerList;

  // 生产方式  ， 工艺路径， 制造路径
  private ApsSchedulingDayConfigVersionProductType productType;


}

