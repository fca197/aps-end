package com.olivia.peanut.portal.api.entity.goods;


import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 商品信息(Goods)表实体类
 *
 * @author peanut
 * @since 2024-03-10 22:22:32
 */
@Data
@Accessors(chain = true)
public class GoodsUpdateIdReq {

  @NotNull(message = "修改信息不能为空")
  private Long id;
  /**
   * 品牌ID
   **/
  private Long brandId;
  /**
   * 商品名称
   **/
  private String goodsName;
  /**
   * 商品图片
   **/
  private String goodsImg;
  /**
   * 条形码
   **/
  private String goodsBarCode;
  /**
   * 二维码
   **/
  private String goodsQrCode;
  /**
   * 最低价格 单位分
   **/
  private BigDecimal goodsMinPrice;
  /**
   * 最高价格 单位分
   **/
  private BigDecimal goodsMaxPrice;
  /**
   * 单位
   **/
  private String goodsUnit;
  /**
   * 产品类型
   **/
  private Long goodsType;
  /**
   * 所属租户id
   **/
  private Long tenantId;
  /**
   * 链路追踪ID
   **/
  private String traceId;
  /**
   * 版本号
   **/
  private Integer versionNum;
}


