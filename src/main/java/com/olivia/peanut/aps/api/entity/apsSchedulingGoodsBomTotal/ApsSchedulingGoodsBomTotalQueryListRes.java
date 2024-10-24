package com.olivia.peanut.aps.api.entity.apsSchedulingGoodsBomTotal;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 订单商品零件汇总表(ApsSchedulingGoodsBomTotal)查询对象返回
 *
 * @author peanut
 * @since 2024-06-02 22:04:08
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingGoodsBomTotalQueryListRes {

  /***
   * 返回对象列表
   */
  private List<ApsSchedulingGoodsBomTotalDto> dataList;


}

