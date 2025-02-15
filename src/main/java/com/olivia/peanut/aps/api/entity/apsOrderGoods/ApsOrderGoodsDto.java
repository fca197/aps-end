package com.olivia.peanut.aps.api.entity.apsOrderGoods;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * (ApsOrderGoods)查询对象返回
 *
 * @author peanut
 * @since 2024-04-09 13:19:36
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderGoodsDto extends BaseEntityDto {

  private Long orderId;
  private Long goodsId;
  private String goodsName;
  private String goodsRemark;
  private BigDecimal goodsAmount;
  private BigDecimal goodsPrice;
  private BigDecimal goodsTotalPrice;
  private String goodsUnit;
  private BigDecimal goodsUnitPrice;
  private BigDecimal goodsUnitTotalPrice;
  private Long factoryId;
  private Long apsStatusId;
}


