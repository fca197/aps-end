package com.olivia.peanut.base.api.entity.h3;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class GridDiskReq {

  @NotNull
  private Long h3Code;
  @Min(value = 1)
  @Max(value = 15)
  private Integer res;
}
