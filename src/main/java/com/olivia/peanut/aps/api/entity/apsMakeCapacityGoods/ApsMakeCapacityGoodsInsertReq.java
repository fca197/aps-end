package com.olivia.peanut.aps.api.entity.apsMakeCapacityGoods;

import com.olivia.peanut.aps.api.entity.apsMakeCapacityFactory.MakeCapacityConfig;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsMakeCapacityGoods)保存入参
 *
 * @author peanut
 * @since 2024-04-13 12:05:05
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsMakeCapacityGoodsInsertReq extends ApsMakeCapacityGoodsDto {


  private List<MakeCapacityConfig> makeCapacityConfigList;

  public void checkParam() {
  }
}

