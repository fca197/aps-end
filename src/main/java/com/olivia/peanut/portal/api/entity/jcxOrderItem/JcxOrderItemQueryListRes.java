package com.olivia.peanut.portal.api.entity.jcxOrderItem;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (JcxOrderItem)查询对象返回
 *
 * @author peanut
 * @since 2024-03-22 10:38:07
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class JcxOrderItemQueryListRes {

  /***
   * 返回对象列表
   */
  private List<JcxOrderItemDto> dataList;


}

