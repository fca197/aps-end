package com.olivia.peanut.aps.api.entity.apsMakeCapacityFactory;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * (ApsMakeCapacityFactory)查询对象返回
 *
 * @author peanut
 * @since 2024-04-13 12:05:04
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsMakeCapacityFactoryQueryListRes {

  /***
   * 返回对象列表
   */
  private List<ApsMakeCapacityFactoryDto> dataList;


}

