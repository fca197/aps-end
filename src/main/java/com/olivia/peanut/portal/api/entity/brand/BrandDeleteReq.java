package com.olivia.peanut.portal.api.entity.brand;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class BrandDeleteReq {

  @NotNull
  @Size(min = 1, max = 100, message = "删除数据长度必须在1到100之间")
  private List<Long> idList;
}
