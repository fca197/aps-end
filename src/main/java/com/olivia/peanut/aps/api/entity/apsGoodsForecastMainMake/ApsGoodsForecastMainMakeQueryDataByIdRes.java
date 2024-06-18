package com.olivia.peanut.aps.api.entity.apsGoodsForecastMainMake;

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
public class ApsGoodsForecastMainMakeQueryDataByIdRes extends JSONObject {

  private String saleConfigCode;
}
