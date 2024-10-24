package com.olivia.peanut.aps.api.entity.apsOrderGoodsSaleConfig;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsOrderGoodsSaleConfig)查询对象返回
 *
 * @author peanut
 * @since 2024-04-09 13:19:38
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderGoodsSaleConfigQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<ApsOrderGoodsSaleConfigDto> dataList;


}

