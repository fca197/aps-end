package com.olivia.peanut.aps.api.entity.apsOrderGoodsForecastMake;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 订单商品节点预测表(ApsOrderGoodsForecastMake)查询对象返回
 *
 * @author peanut
 * @since 2024-06-02 23:11:41
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderGoodsForecastMakeDto extends BaseEntityDto {

  private Long orderId;
  /***
   *  商品ID
   */
  private Long goodsId;
  /***
   *  订单状态
   */
  private Long goodsStatusId;
  /***
   *  节点名称
   */
  private String goodsStatusName;
  /***
   *  预计制造日期
   */
  private LocalDateTime forecastMakeDate;
  /***
   *  工厂ID
   */
  private Long factoryId;

}


