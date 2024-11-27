package com.olivia.peanut.aps.api.entity.apsSaleConfig;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * (ApsSaleConfig)查询对象返回
 *
 * @author peanut
 * @since 2024-03-29 23:10:39
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSaleConfigQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<ApsSaleConfigDto> dataList;


}

