package com.olivia.peanut.aps.api.entity.apsProjectConfig;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * (ApsProjectConfig)查询对象返回
 *
 * @author peanut
 * @since 2024-03-30 16:21:20
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsProjectConfigQueryListRes {

  /***
   * 返回对象列表
   */
  private List<ApsProjectConfigDto> dataList;


}

