package com.olivia.peanut.portal.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (JcxBuyOrderItem)表实体类
 *
 * @author peanut
 * @since 2024-03-27 13:51:37
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("jcx_buy_order_item")
public class JcxBuyOrderItem extends BaseEntity {

  private Long orderId;
  private Long goodsId;
  private String goodsName;
  private BigDecimal goodsCostPrice;
  private Long goodsBuyCount;
  private String goodsUnit;
  private BigDecimal goodsCostPriceTotal;
  private String goodsBuyRemark;
  private Long supplierId;
  private String supplierName;
}

