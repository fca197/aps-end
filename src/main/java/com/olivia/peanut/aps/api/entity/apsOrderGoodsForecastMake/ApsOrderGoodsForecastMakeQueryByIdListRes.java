package com.olivia.peanut.aps.api.entity.apsOrderGoodsForecastMake;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 订单商品节点预测表(ApsOrderGoodsForecastMake)查询对象返回
 *
 * @author peanut
 * @since 2024-06-02 23:11:41
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderGoodsForecastMakeQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<ApsOrderGoodsForecastMakeDto> dataList;


}

