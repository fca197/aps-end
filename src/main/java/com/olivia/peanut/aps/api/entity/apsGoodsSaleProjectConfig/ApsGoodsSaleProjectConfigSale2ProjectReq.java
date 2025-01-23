package com.olivia.peanut.aps.api.entity.apsGoodsSaleProjectConfig;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class ApsGoodsSaleProjectConfigSale2ProjectReq {

  @NotNull(message = "goodsId不能为空")
  private Long goodsId;
  @NotBlank(message = "销售配置不能为空")
  private String saleConfig;
  @NotNull(message = "转换数量不能为空")
  @Min(value = 1, message = "转换数量不能小于1")
  @Max(value = 200000, message = "转换数量不能大于200000")
  private Long convertCount;
  private LocalDate bizKey;
  private Long id;
}
