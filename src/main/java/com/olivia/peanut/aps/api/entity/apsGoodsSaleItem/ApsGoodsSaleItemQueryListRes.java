package com.olivia.peanut.aps.api.entity.apsGoodsSaleItem;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * (ApsGoodsSaleItem)查询对象返回
 *
 * @author peanut
 * @since 2024-03-30 10:38:36
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsSaleItemQueryListRes {

  /***
   * 返回对象列表
   */
  private List<ApsGoodsSaleItemDto> dataList;


}

