package com.olivia.peanut.portal.api.entity.jcxBuyOrder;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (JcxBuyOrder)查询对象返回
 *
 * @author peanut
 * @since 2024-03-27 13:51:36
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class JcxBuyOrderQueryListRes {

  /***
   * 返回对象列表
   */
  private List<JcxBuyOrderDto> dataList;


}

