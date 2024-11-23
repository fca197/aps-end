package com.olivia.peanut.portal.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 商品信息(Goods)表实体类
 *
 * @author makejava
 * @since 2024-03-11 10:44:05
 */
@Accessors(chain = true)
@Getter
@Setter
//
@TableName("jcx_goods")
public class JcxGoods extends BaseEntity {

  /***
   *  品牌ID
   */
  private Long brandId;
  /***
   *  商品名称
   */
  private String goodsName;
  /***
   *  商品图片
   */
  private String goodsImg;
  /***
   *  条形码
   */
  private String goodsBarCode;
  /***
   *  二维码
   */
  private String goodsQrCode;
  /***
   * 成本价
   */
  private BigDecimal costPrice;
  /***
   * 销售价格
   */
  private BigDecimal salesPrice;

  /***
   * 毛利(分)
   */
  private BigDecimal goodsGrossProfit;
  /**
   * 净利(分)
   */
  private BigDecimal goodsNetProfit;
  /***
   *  规格
   */
  private String goodsUnit;
  /***
   *  产品类型
   */
  private Long goodsType = -1L;

  /***
   * 库存
   */
  private BigDecimal goodsInventoryCount;
  /***
   * 预警库存
   */
  private Integer warningCount = 10;
  /**
   * 是否盘点
   */
  private Boolean isInventory;

  private Long supplierId;

}

