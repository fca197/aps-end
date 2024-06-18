package com.olivia.peanut.aps.api.entity.apsSchedulingGoodsBom;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 订单商品零件表(ApsSchedulingGoodsBom)查询对象入参
 *
 * @author peanut
 * @since 2024-06-02 21:50:25
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingGoodsBomQueryByIdListReq {

  private List<Long> idList;


  public void checkParam() {
  }

}

