package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsOrderGoodsProjectConfig)表实体类
 *
 * @author peanut
 * @since 2024-04-09 13:19:37
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_order_goods_project_config")
public class ApsOrderGoodsProjectConfig extends BaseEntity {

  private Long orderId;
  private Long goodsId;
  private Long configId;
  private Long factoryId;

}

