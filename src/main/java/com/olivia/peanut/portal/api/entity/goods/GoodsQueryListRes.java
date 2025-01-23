package com.olivia.peanut.portal.api.entity.goods;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品信息(Goods)查询对象返回
 *
 * @author makejava
 * @since 2024-03-11 10:44:05
 */
@Accessors(chain = true)
@Getter
@Setter

public class GoodsQueryListRes {

  /***
   * 返回对象列表
   */
  private List<Info> dataList;


  @Getter
  @Setter
  public static class Info extends BaseEntityDto {

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
     *  最低价格 规格分
     */
    private BigDecimal goodsMinPrice;
    /***
     *  最高价格 规格分
     */
    private BigDecimal goodsMaxPrice;
    /***
     *  规格
     */
    private String goodsUnit;
    /***
     *  产品类型
     */
    private Long goodsType;


  }
}

