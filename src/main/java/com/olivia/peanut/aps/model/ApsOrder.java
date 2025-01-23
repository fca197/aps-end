package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.peanut.aps.api.entity.apsOrder.ApsOrderStatusEnum;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
  /***
   *@see   ApsOrderStatusEnum
   */

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


  public ApsOrder setOrderStatus(@NonNull Long apsOrderStatus) {
    this.orderStatus = apsOrderStatus;
    return this;
  }

  public ApsOrder setOrderStatus(@NonNull ApsOrderStatusEnum apsOrderStatusEnum) {

    this.orderStatus = apsOrderStatusEnum.getCode();
    return this;
  }

}

