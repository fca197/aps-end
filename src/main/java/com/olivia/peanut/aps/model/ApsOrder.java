package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsOrder)表实体类
 *
 * @author peanut
 * @since 2024-04-09 13:19:35
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_order")
public class ApsOrder extends BaseEntity {

  private String orderNo;
  private String orderNoParent;
  private String orderRemark;
  private Long orderStatus;
  private BigDecimal orderTotalPrice;
  private BigDecimal reserveAmount;
  private LocalDateTime reserveDatetime;
  private BigDecimal finishPayedAmount;
  private LocalDateTime finishPayedDatetime;
  private LocalDate makeFinishDate;
  private LocalDate deliveryDate;
  private Long factoryId;

  private LocalDate schedulingDate;
  /**
   * 越大越紧急
   */
  private Integer urgencyLevel;

}

