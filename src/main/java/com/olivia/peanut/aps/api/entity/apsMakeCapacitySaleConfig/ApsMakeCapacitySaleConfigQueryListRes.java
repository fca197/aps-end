package com.olivia.peanut.aps.api.entity.apsMakeCapacitySaleConfig;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsMakeCapacitySaleConfig)查询对象返回
 *
 * @author peanut
 * @since 2024-04-13 12:05:06
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsMakeCapacitySaleConfigQueryListRes {

  /***
   * 返回对象列表
   */
  private List<ApsMakeCapacitySaleConfigDto> dataList;


}

