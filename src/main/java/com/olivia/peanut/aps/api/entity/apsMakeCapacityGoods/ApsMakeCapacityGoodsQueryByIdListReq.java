package com.olivia.peanut.aps.api.entity.apsMakeCapacityGoods;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsMakeCapacityGoods)查询对象入参
 *
 * @author peanut
 * @since 2024-04-13 12:05:06
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsMakeCapacityGoodsQueryByIdListReq {

  private List<Long> idList;


  public void checkParam() {
  }

}

