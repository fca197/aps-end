package com.olivia.peanut.portal.api.entity.shiftItem;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ShiftItem)查询对象返回
 *
 * @author peanut
 * @since 2024-04-04 12:10:16
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ShiftItemQueryListRes {

  /***
   * 返回对象列表
   */
  private List<ShiftItemDto> dataList;


}

