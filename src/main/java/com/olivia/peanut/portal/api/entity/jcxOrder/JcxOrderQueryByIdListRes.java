package com.olivia.peanut.portal.api.entity.jcxOrder;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * (JcxOrder)查询对象返回
 *
 * @author peanut
 * @since 2024-03-22 10:38:06
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class JcxOrderQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<JcxOrderDto> dataList;


}

