package com.olivia.peanut.portal.api.entity.districtCode;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import lombok.Getter;
import lombok.Setter;

/**
 * (DistrictCode)查询对象返回
 *
 * @author peanut
 * @since 2024-04-09 13:19:07
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class DistrictCodeDto extends BaseEntityDto {

  private String code;
  private String name;
  private String parentCode;
  private Integer level;

}


