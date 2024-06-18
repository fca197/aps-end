package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsSchedulingVersionDay)表实体类
 *
 * @author peanut
 * @since 2024-04-23 14:37:05
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_scheduling_version_day")
public class ApsSchedulingVersionDay extends BaseEntity {

  private Long versionId;
  private String currentDay;
  private Boolean hasEnough;

}

