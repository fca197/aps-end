package com.olivia.peanut.portal.api.entity.jcxBuyPlanItem;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * (JcxBuyPlanItem)查询对象返回
 *
 * @author peanut
 * @since 2024-03-24 20:27:12
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class JcxBuyPlanItemQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<JcxBuyPlanItemDto> dataList;


}

