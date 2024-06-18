package com.olivia.peanut.aps.api.entity.apsSchedulingConstraints;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsSchedulingConstraints)查询对象返回
 *
 * @author peanut
 * @since 2024-04-13 21:32:13
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingConstraintsQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<ApsSchedulingConstraintsDto> dataList;


}

