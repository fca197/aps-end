package com.olivia.peanut.aps.api.entity.apsGoods;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoods)查询对象返回
 *
 * @author peanut
 * @since 2024-03-29 16:11:23
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsQueryListRes {

  /***
   * 返回对象列表
   */
  private List<ApsGoodsDto> dataList;


}

