package com.olivia.peanut.aps.api.entity.apsSchedulingGoodsBomTotal;

import com.olivia.peanut.aps.api.entity.apsGoodsBom.ApsGoodsBomDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Objects;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class ApsSchedulingGoodsBomTotalQueryBomTotalRes extends HashMap<String, Object> {

  private String bomName;

  private ApsGoodsBomDto apsGoodsBomDto;

  public boolean isEnough() {
    return Objects.equals(1, getOrDefault("enough", 0));
  }

  public String getIsNotFollow() {
    return (String) get("isNotFollow");
  }
}
