package com.olivia.peanut.aps.api.entity.apsSchedulingVersion;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsSchedulingVersion)查询对象返回
 *
 * @author peanut
 * @since 2024-04-13 21:32:14
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingVersionQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<ApsSchedulingVersionDto> dataList;


}

