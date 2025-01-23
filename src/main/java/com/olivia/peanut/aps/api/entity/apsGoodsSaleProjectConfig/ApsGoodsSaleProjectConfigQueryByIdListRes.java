package com.olivia.peanut.aps.api.entity.apsGoodsSaleProjectConfig;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * (ApsGoodsSaleProjectConfig)查询对象返回
 *
 * @author peanut
 * @since 2024-04-27 16:07:23
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsSaleProjectConfigQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<ApsGoodsSaleProjectConfigDto> dataList;


}

