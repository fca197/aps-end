package com.olivia.peanut.aps.api.entity.apsGoodsForecastMainGoodsData;

import com.alibaba.fastjson2.JSONObject;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class GetDataByGoodsIdRes extends JSONObject {

  private String saleCode;
}
