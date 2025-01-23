package com.olivia.peanut.aps.api.entity.apsSchedulingVersionItem;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * (ApsSchedulingVersionItem)查询对象返回
 *
 * @author peanut
 * @since 2024-04-16 09:24:05
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingVersionItemQueryListRes {

  /***
   * 返回对象列表
   */
  private List<ApsSchedulingVersionItemDto> dataList;


}

