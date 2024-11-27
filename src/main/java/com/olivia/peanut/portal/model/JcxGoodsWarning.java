package com.olivia.peanut.portal.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * (JcxGoodsWarning)表实体类
 *
 * @author peanut
 * @since 2024-03-24 14:10:55
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("jcx_goods_warning")
public class JcxGoodsWarning extends BaseEntity {


  private String reportNo;
  private Long goodsId;
  private String goodsName;
  private BigDecimal costPrice;
  private BigDecimal salesPrice;
  private Integer warningCount;
  private BigDecimal goodsGrossProfit;
  private BigDecimal goodsNetProfit;
  private BigDecimal goodsInventoryCount;
  private Boolean isInventory;
  private Boolean isDone;

}

