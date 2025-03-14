package com.olivia.peanut.base.api.entity.districtCode;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * (DistrictCode)查询对象返回
 *
 * @author peanut
 * @since 2024-04-09 13:19:07
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class DistrictCodeQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<DistrictCodeDto> dataList;


}

