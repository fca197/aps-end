package com.olivia.peanut.portal.api.entity.storey;

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
public class StoreyAddBatchReq {

  @NotNull(message = "工厂不能为空")
  private Long factoryId;
  @NotNull(message = "楼层数量不能为空")
  @Min(value = 1, message = "楼层数量不能小于{min}")
  @Max(value = 100, message = "楼层数量不能大于{max}")
  private Integer storeyCount;
  private String storeyCode;
  private Boolean isPrefix;

  public boolean isPrefix() {
    return Boolean.TRUE.equals(isPrefix);
  }
}
