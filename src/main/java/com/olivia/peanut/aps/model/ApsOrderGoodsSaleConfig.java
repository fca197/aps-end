package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsOrderGoodsSaleConfig)表实体类
 *
 * @author peanut
 * @since 2024-04-09 13:19:38
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_order_goods_sale_config")
public class ApsOrderGoodsSaleConfig extends BaseEntity {

  private Long orderId;
  private Long goodsId;
  private Long configId;
  private Long factoryId;

}

