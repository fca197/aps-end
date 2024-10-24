package com.olivia.peanut.aps.api.entity.apsGoodsForecastMainGoodsData;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecastMainGoodsData)查询对象入参
 *
 * @author peanut
 * @since 2024-04-02 13:44:29
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastMainGoodsDataQueryByIdListReq {

  private List<Long> idList;


  public void checkParam() {
  }

}

