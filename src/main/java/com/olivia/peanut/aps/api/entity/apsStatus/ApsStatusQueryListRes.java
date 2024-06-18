package com.olivia.peanut.aps.api.entity.apsStatus;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsStatus)查询对象返回
 *
 * @author peanut
 * @since 2024-04-01 10:50:12
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsStatusQueryListRes {

  /***
   * 返回对象列表
   */
  private List<ApsStatusDto> dataList;


}

