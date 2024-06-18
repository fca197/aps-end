package com.olivia.peanut.aps.api.entity.apsOrderGoodsProjectConfig;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsOrderGoodsProjectConfig)查询对象返回
 *
 * @author peanut
 * @since 2024-04-09 13:19:37
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderGoodsProjectConfigQueryListRes {

  /***
   * 返回对象列表
   */
  private List<ApsOrderGoodsProjectConfigDto> dataList;


}

