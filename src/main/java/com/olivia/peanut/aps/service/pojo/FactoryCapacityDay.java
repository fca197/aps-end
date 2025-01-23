package com.olivia.peanut.aps.service.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;

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
