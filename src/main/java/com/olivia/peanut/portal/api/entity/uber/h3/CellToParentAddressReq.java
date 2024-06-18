package com.olivia.peanut.portal.api.entity.uber.h3;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class CellToParentAddressReq {

  @NotBlank
  Long h3Code;
  private int res;
}
