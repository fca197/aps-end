package com.olivia.peanut.portal.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * (JcxBuyOrder)表实体类
 *
 * @author peanut
 * @since 2024-03-27 13:51:36
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("jcx_buy_order")
public class JcxBuyOrder extends BaseEntity {

  private String orderNo;
  private String orderName;
  private String goodsName;
  private BigDecimal goodsCostPriceTotal;
  private String orderRemark;
  private Integer orderStatus;

}

