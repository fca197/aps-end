package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * (ApsGoodsBom)表实体类
 *
 * @author peanut
 * @since 2024-04-03 22:28:56
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_goods_bom")
public class ApsGoodsBom extends BaseEntity {

  // 商品ID
  private Long goodsId;
  private Long bomId;
  //零件编号
  private String bomCode;
  // 名称
  private String bomName;
  // 用量
  private BigDecimal bomUsage;
  //规格
  private String bomUnit;
  // 成本价
  private BigDecimal bomCostPrice;
  // 成本价规格
  private String bomCostPriceUnit;
  //使用工位
  private Long bomUseWorkStation;
  //使用表达 工程值: 格式 . 所有工序  (AA001&&AC002)&&(AB001||AB002)
  private String bomUseExpression;
  //工厂ID
  private Long factoryId;
  /***
   * 是否关注
   */
  private Boolean isFollow;
}

