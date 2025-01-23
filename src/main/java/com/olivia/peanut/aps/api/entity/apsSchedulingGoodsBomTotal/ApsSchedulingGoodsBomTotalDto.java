package com.olivia.peanut.aps.api.entity.apsSchedulingGoodsBomTotal;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 订单商品零件汇总表(ApsSchedulingGoodsBomTotal)查询对象返回
 *
 * @author peanut
 * @since 2024-06-02 22:04:08
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingGoodsBomTotalDto extends BaseEntityDto {

  /***
   *  订单ID
   */
  private Long schedulingId;
  /***
   *  零件ID
   */
  private Long bomId;
  /***
   *  bom 编码
   */
  private String bomCode;
  /***
   *  bom 名称
   */
  private String bomName;
  /***
   *  使用量
   */
  private BigDecimal bomUsage;
  /***
   *  规格
   */
  private String bomUnit;
  /***
   *  成本价
   */
  private BigDecimal bomCostPrice;
  /***
   *  规格
   */
  private String bomCostPriceUnit;
  /***
   *  使用工位
   */
  private Long bomUseWorkStation;
  /***
   *  使用时间
   */
  private LocalDate bomUseDate;
  /***
   *  工厂ID
   */
  private Long factoryId;

}


