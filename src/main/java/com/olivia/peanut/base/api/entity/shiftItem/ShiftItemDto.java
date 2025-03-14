package com.olivia.peanut.base.api.entity.shiftItem;


import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

/**
 * (ShiftItem)查询对象返回
 *
 * @author peanut
 * @since 2024-04-04 12:10:16
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ShiftItemDto extends BaseEntityDto {


  private Long shiftId;
  private LocalTime beginTime;
  private LocalTime endTime;
  private Long factoryId;

}


