package com.olivia.peanut.aps.api.entity.apsGoodsForecastMainGoodsData;

import java.util.HashMap;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class GetDataByGoodsIdRes extends HashMap<String, Object> {

  private String saleCode;
}
