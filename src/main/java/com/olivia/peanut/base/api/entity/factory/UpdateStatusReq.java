package com.olivia.peanut.base.api.entity.factory;

import jakarta.validation.constraints.NotBlank;
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
public class UpdateStatusReq {

  @NotNull
  private Long id;
  @NotBlank
  private String status;
}
