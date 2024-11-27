package com.olivia.peanut.aps.api.entity.apsBomGroup;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 零件组配置(ApsBomGroup)查询对象返回
 *
 * @author peanut
 * @since 2024-06-19 17:41:23
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsBomGroupQueryListRes {

  /***
   * 返回对象列表
   */
  private List<ApsBomGroupDto> dataList;


}

