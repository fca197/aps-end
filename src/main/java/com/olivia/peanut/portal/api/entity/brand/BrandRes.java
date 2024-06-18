package com.olivia.peanut.portal.api.entity.brand;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class BrandRes {

  private Long id;
  /**
   * 所属租户id
   **/
  private Long tenantId;
  /**
   * 所属工厂id
   **/
  private Long factoryId;
  /**
   * 品牌编码
   **/
  private String brandCode;
  /**
   * 品牌名称
   **/
  private String brandName;
  /**
   * 品牌状态
   **/
  private String brandStatus;
}
