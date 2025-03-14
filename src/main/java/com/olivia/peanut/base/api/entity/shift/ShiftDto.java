package com.olivia.peanut.base.api.entity.shift;

import com.olivia.peanut.base.api.entity.shiftItem.ShiftItemDto;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * (Shift)查询对象返回
 *
 * @author peanut
 * @since 2024-04-04 12:10:15
 */
//@Accessors(chain=true)
@Getter
@Setter
@Accessors(chain = true)
@SuppressWarnings("serial")
public class ShiftDto extends BaseEntityDto {

  private String shiftCode;
  private String shiftName;
  private Long factoryId;
  private String factoryName;

  private List<ShiftItemDto> shiftItemDtoList;
}


