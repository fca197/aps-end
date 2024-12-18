package com.olivia.peanut.aps.service.impl.po;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.Map;

@Setter
@Getter
@Accessors(chain = true)
public class ApsBomEmail {
  private String bomName;
  private String bomCostPriceUnit;
  private Map<LocalDate, Object> buyMap;
}
