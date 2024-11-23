package com.olivia.peanut.portal.api.entity.goods;

import com.alibaba.excel.annotation.ExcelProperty;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * 商品信息(Goods)查询对象返回
 *
 * @author makejava
 * @since 2024-03-11 10:44:06
 */
//@Accessors(chain=true)
@Getter
@Setter

public class JcxGoodsDto extends BaseEntityDto {


  /***
   *  品牌ID
   */
  @ExcelProperty("品牌ID")
  private Long brandId;
  /***
   *  商品名称
   */
  @ExcelProperty("商品名称")
  private String goodsName;
  /***
   *  商品图片
   */
  @ExcelProperty("商品图片")
  private String goodsImg;
  /***
   *  条形码
   */
  @ExcelProperty("条形码")
  private String goodsBarCode;
  /***
   *  二维码
   */
  @ExcelProperty("二维码")
  private String goodsQrCode;
  /***
   *  最低价格 规格分
   */
  @ExcelProperty("成本价(分)")

  private BigDecimal costPrice;
  /***
   *  最高价格 规格分
   */
  @ExcelProperty("售卖价(分)")
  private BigDecimal salesPrice;
  /***
   *  规格
   */
  @ExcelProperty("规格")
  private String goodsUnit;
  /***
   *  产品类型
   */
  @ExcelProperty("产品类型")
  private Long goodsType;

  private Integer warningCount;


  /***
   * 库存
   */
  private BigDecimal goodsInventoryCount;


  /***
   * 毛利(分)
   */
  private BigDecimal goodsGrossProfit;
  /**
   * 净利(分)
   */
  private BigDecimal goodsNetProfit;
  /**
   * 是否盘点
   */
  private Boolean isInventory;

  private Long supplierId;
}


