package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsSchedulingVersionLimit)表实体类
 *
 * @author peanut
 * @since 2024-04-19 14:56:59
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_scheduling_version_limit")
public class ApsSchedulingVersionLimit extends BaseEntity {

  private Long versionId;
  private String currentDay;
  private String showName;
  private String fieldName;
  private String fieldValue;
  private Integer currentCount;
  private Integer min;
  private Integer max;
  private String limitType;
}

