package com.olivia.peanut.aps.service.pojo;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class FactoryCapacityDay {

  private LocalDate localDate;
  private Integer capacity;
}
