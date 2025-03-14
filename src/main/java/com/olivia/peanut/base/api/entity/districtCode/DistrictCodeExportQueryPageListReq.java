package com.olivia.peanut.base.api.entity.districtCode;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (DistrictCode)查询对象入参
 *
 * @author peanut
 * @since 2024-04-09 13:19:07
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class DistrictCodeExportQueryPageListReq {

  private int pageNum;
  private int pageSize;
  private Boolean queryPage = true;
  private DistrictCodeDto data;


}

