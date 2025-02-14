package com.olivia.peanut.aps.api.entity.apsOrder;

import com.olivia.peanut.aps.api.entity.apsOrderGoods.ApsOrderGoodsDto;
import com.olivia.peanut.aps.api.entity.apsOrderGoodsBom.ApsOrderGoodsBomDto;
import com.olivia.peanut.aps.api.entity.apsOrderGoodsProjectConfig.ApsOrderGoodsProjectConfigDto;
import com.olivia.peanut.aps.api.entity.apsOrderGoodsSaleConfig.ApsOrderGoodsSaleConfigDto;
import com.olivia.peanut.aps.api.entity.apsOrderUser.ApsOrderUserDto;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * (ApsOrder)查询对象返回
 *
 * @author peanut
 * @since 2024-04-09 13:19:36
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderDto extends BaseEntityDto {


  //  @MaskValue(value = DesensitizedType.FIRST_MASK)
  private String orderNo;
  private String orderNoParent;
  private String orderRemark;
  private Long orderStatus;
  private String orderStatusName;
  private Long orderGoodsStatus;
  private String orderStatusGoodsName;
  private BigDecimal orderTotalPrice;
  private BigDecimal reserveAmount;
  private LocalDateTime reserveDatetime;
  private BigDecimal finishPayedAmount;
  private LocalDateTime finishPayedDatetime;
  private LocalDate makeFinishDate;
  private LocalDate deliveryDate;
  private Long factoryId;
  /**
   * 越大越紧急
   */
  private Integer urgencyLevel;
  private LocalDate schedulingDate;
  private ApsOrderUserDto orderUser;
  private List<ApsOrderGoodsDto> goodsList;
  private List<ApsOrderGoodsProjectConfigDto> goodsProjectConfigList;
  private List<ApsOrderGoodsSaleConfigDto> goodsSaleConfigList;
  private List<ApsOrderGoodsBomDto> apsOrderGoodsBomList;
}


