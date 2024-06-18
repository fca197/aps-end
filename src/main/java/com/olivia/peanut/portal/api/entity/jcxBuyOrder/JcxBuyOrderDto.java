package com.olivia.peanut.portal.api.entity.jcxBuyOrder;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.peanut.portal.api.entity.jcxBuyOrderItem.JcxBuyOrderItemDto;
import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * (JcxBuyOrder)查询对象返回
 *
 * @author peanut
 * @since 2024-03-27 13:51:37
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class JcxBuyOrderDto extends BaseEntityDto {


  List<JcxBuyOrderItemDto> buyOrderItemDtoList;
  private String goodsName;
  private BigDecimal goodsCostPriceTotal;
  private String orderNo;
  private String orderName;
  private String orderRemark;
  private Integer orderStatus;
  private String supplierName;

  @Override
  public void checkParam() {

  }
}


