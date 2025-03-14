package com.olivia.peanut.base.api.entity.ding;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class CodeLoginRes {
  private String token;
}
