package com.olivia.peanut.portal.api.entity.property;

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
public class PropertySaveBatchReq {

  @NotNull(message = "工厂不能为空")
  private Long factoryId;
  @NotNull(message = "楼层不能为空")
  private Long storeyId;
  @NotNull(message = "房间不能为空")
  private Long roomId;
  private String propertyPrefix;
  @NotNull(message = "资产名称不能为空")
  private String propertyName;
  @NotNull(message = "资产数量不能为空")
  @Min(value = 1, message = "资产数量不能小于{min}")
  @Max(value = 300, message = "资产数量不能大于{max}")
  private Integer propertyCount;
}
