package com.olivia.peanut.portal.api.entity.brand;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class BrandUpdateReq {

  @NotNull(message = "id不能为空")
  Long id;

  /**
   * 所属工厂id
   **/
  @NotNull(message = "所属工厂id不能为空")
  private Long factoryId;

  @NotNull(message = "品牌编码不能为空")
  @Size(min = 3, max = 10, message = "品牌编码长度必须在3到10之间")
  private String brandCode;
  @NotNull(message = "品牌名称不能为空")
  @Size(min = 3, max = 10, message = "品牌名称长度必须在3到10之间")
  private String brandName;
  /**
   * 品牌状态
   **/
  private String brandStatus;

  private Integer versionNum;
}
