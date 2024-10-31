package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * BOM 清单(ApsBom)表实体类
 *
 * @author peanut
 * @since 2024-06-06 11:27:34
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_bom")
public class ApsBom extends BaseEntity {

  private Long groupId;

  /***
   *  bom 编码
   */
  private String bomCode;
  /***
   *  bom 名称
   */
  private String bomName;
  /***
   *  成本价
   */
  private BigDecimal bomCostPrice;
  /***
   *  单位
   */
  private String bomCostPriceUnit;
  /***
   *  库存
   */
  private BigDecimal bomInventory;

}

