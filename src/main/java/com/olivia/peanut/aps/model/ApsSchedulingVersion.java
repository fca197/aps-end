package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsSchedulingVersion)表实体类
 *
 * @author peanut
 * @since 2024-04-13 21:32:14
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_scheduling_version")
public class ApsSchedulingVersion extends BaseEntity {

  private String schedulingVersionNo;
  private String schedulingVersionName;
  private Long schedulingConstraintsId;
  private String headerList;
  private String capacityHeaderList;
  private String capacityDateList;
  private Integer schedulingDayCount;
  // 1 第一步 ,开始第二步计算: 20 ,25步异常 ,第三步 30  三步异常35 3步结束 40  结束 100
  private Integer versionStep;
  private String versionStepError;
//
  /**
   * bom 统计截止日期
   */
  private LocalDate bomTotalEndDate;
}

