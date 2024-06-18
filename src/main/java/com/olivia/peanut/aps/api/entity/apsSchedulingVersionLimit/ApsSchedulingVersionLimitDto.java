package com.olivia.peanut.aps.api.entity.apsSchedulingVersionLimit;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import lombok.Getter;
import lombok.Setter;

/**
 * (ApsSchedulingVersionLimit)查询对象返回
 *
 * @author peanut
 * @since 2024-04-19 14:57:00
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingVersionLimitDto extends BaseEntityDto {

  private Long versionId;
  private String currentDay;
  private String showName;
  private String fieldName;
  private String fieldValue;
  private Integer currentCount;
  private Integer min;
  private Integer max;

}


