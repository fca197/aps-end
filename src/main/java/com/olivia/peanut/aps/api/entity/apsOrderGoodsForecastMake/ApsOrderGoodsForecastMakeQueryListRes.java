package com.olivia.peanut.aps.api.entity.apsOrderGoodsForecastMake;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 订单商品节点预测表(ApsOrderGoodsForecastMake)查询对象返回
 *
 * @author peanut
 * @since 2024-06-02 23:11:40
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderGoodsForecastMakeQueryListRes {

  /***
   * 返回对象列表
   */
  private List<ApsOrderGoodsForecastMakeDto> dataList;


}

