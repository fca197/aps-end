package com.olivia.peanut.portal.api.entity.goods;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 商品信息(Goods)查询对象返回
 *
 * @author makejava
 * @since 2024-03-11 10:44:06
 */
@Accessors(chain = true)
@Getter
@Setter

public class GoodsQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<JcxGoodsDto> dataList;


}

