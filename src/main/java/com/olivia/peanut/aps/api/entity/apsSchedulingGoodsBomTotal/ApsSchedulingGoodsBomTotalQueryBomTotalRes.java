package com.olivia.peanut.aps.api.entity.apsSchedulingGoodsBomTotal;

import com.alibaba.fastjson2.JSONObject;
import com.olivia.peanut.aps.api.entity.apsGoodsBom.ApsGoodsBomDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class ApsSchedulingGoodsBomTotalQueryBomTotalRes extends JSONObject {

  private String bomName;

  private ApsGoodsBomDto apsGoodsBomDto;

  public boolean isEnough() {
    return getIntValue("enough", 0) == 1;
  }

  public String getIsNotFollow() {
    return getString("isNotFollow");
  }
}
