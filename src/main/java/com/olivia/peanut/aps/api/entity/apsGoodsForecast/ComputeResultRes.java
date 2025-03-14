package com.olivia.peanut.aps.api.entity.apsGoodsForecast;

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
public class ComputeResultRes extends HashMap<String, Object> {

  private String saleConfigCode;
}
