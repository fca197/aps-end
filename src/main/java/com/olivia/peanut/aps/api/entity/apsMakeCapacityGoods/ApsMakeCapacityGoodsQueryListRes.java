package com.olivia.peanut.aps.api.entity.apsMakeCapacityGoods;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsMakeCapacityGoods)查询对象返回
 *
 * @author peanut
 * @since 2024-04-13 12:05:05
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsMakeCapacityGoodsQueryListRes {

  /***
   * 返回对象列表
   */
  private List<ApsMakeCapacityGoodsDto> dataList;


}

