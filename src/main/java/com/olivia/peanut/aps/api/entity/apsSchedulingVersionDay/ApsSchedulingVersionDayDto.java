package com.olivia.peanut.aps.api.entity.apsSchedulingVersionDay;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import lombok.Getter;
import lombok.Setter;

/**
 * (ApsSchedulingVersionDay)查询对象返回
 *
 * @author peanut
 * @since 2024-04-23 14:37:06
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingVersionDayDto extends BaseEntityDto {

  private Long versionId;
  private String currentDay;
  private Boolean hasEnough;

}


