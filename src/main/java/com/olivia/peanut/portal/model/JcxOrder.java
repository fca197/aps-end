package com.olivia.peanut.portal.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (JcxOrder)表实体类
 *
 * @author peanut
 * @since 2024-03-22 10:38:06
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("jcx_order")
public class JcxOrder extends BaseEntity {

  private String orderNo;
  private String orderRemark;
  private String orderStatus;
  private BigDecimal orderTotalSalePrice;
}

