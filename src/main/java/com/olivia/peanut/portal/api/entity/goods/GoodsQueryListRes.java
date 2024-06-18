package com.olivia.peanut.portal.api.entity.goods;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

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
  public static class Info {

    /***
     *  id
     */
    private Long id;
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
     *  最低价格 单位分
     */
    private BigDecimal goodsMinPrice;
    /***
     *  最高价格 单位分
     */
    private BigDecimal goodsMaxPrice;
    /***
     *  单位
     */
    private String goodsUnit;
    /***
     *  产品类型
     */
    private Long goodsType;
    /***
     *  所属租户id
     */
    private Long tenantId;
    /***
     *
     */

    /***
     *  创建时间
     */
    private LocalTime createTime;
    /***
     *  创建人id
     */
    private Long createBy;
    /***
     *  更新时间
     */
    private LocalTime updateTime;
    /***
     *  更新人id
     */
    private Long updateBy;
    /***
     *  链路追踪ID
     */
    private String traceId;
    /***
     *  版本号
     */
    private Integer versionNum;

  }
}

