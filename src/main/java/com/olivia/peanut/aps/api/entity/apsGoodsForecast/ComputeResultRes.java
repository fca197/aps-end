package com.olivia.peanut.aps.api.entity.apsGoodsForecast;

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
public class ComputeResultRes extends HashMap<String, Object> {

  private String saleConfigCode;
}
