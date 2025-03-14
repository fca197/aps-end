package com.olivia.peanut.aps.api.entity.apsGoodsForecastMake;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashMap;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class ApsGoodsForecastMakeQueryUseBomByIdRes extends HashMap<String, Object> {

  private String bomName;
}
