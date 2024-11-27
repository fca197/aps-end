package com.olivia.peanut.portal.api.entity.jcxOrder;

import com.alibaba.excel.annotation.ExcelProperty;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.peanut.portal.api.entity.jcxOrderItem.JcxOrderItemDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * (JcxOrder)查询对象返回
 *
 * @author peanut
 * @since 2024-03-22 10:38:06
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class JcxOrderDto extends BaseEntityDto {

  private String orderNo;
  @ExcelProperty("订单备注")
  private String orderRemark;
  private String orderStatus;
  @ExcelProperty("订单状态")
  private String orderStatusName;

  private BigDecimal orderTotalSalePrice;

  private String goodsName;
  private Long supplierId;
  private List<JcxOrderItemDto> orderItemDtoList;
}


