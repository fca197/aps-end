package com.olivia.peanut.base.api.entity.districtCode;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (DistrictCode)保存返回
 *
 * @author peanut
 * @since 2024-04-09 13:19:06
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class DistrictCodeInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

