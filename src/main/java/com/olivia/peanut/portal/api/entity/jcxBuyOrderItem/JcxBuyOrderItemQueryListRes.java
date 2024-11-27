package com.olivia.peanut.portal.api.entity.jcxBuyOrderItem;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * (JcxBuyOrderItem)查询对象返回
 *
 * @author peanut
 * @since 2024-03-27 13:51:37
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class JcxBuyOrderItemQueryListRes {

  /***
   * 返回对象列表
   */
  private List<JcxBuyOrderItemDto> dataList;


}

