package com.olivia.peanut.aps.model;


import java.time.LocalDate;
import java.time.LocalDateTime;
import com.olivia.sdk.utils.BaseEntity;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;

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
}

