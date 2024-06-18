package com.olivia.peanut.aps.api.entity.apsSchedulingGoodsBom;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 订单商品零件表(ApsSchedulingGoodsBom)查询对象返回
 *
 * @author peanut
 * @since 2024-06-02 21:50:25
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingGoodsBomQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<ApsSchedulingGoodsBomDto> dataList;


}

