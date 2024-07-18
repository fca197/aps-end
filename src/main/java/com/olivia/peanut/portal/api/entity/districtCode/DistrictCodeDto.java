package com.olivia.peanut.portal.api.entity.districtCode;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import java.util.List;
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

  // 0国,1省,2市,3区
  private Integer level;
  private String path;
  private List<DistrictCodeDto> children;
}


