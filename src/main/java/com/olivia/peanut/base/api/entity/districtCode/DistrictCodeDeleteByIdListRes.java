package com.olivia.peanut.base.api.entity.districtCode;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (DistrictCode)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-04-09 13:19:06
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class DistrictCodeDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

