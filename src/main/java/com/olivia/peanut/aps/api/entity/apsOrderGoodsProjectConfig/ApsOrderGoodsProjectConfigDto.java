package com.olivia.peanut.aps.api.entity.apsOrderGoodsProjectConfig;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import lombok.Getter;
import lombok.Setter;

/**
 * (ApsOrderGoodsProjectConfig)查询对象返回
 *
 * @author peanut
 * @since 2024-04-09 13:19:37
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderGoodsProjectConfigDto extends BaseEntityDto {

  private Long orderId;
  private Long goodsId;
  private Long configId;
  private Long factoryId;

}


