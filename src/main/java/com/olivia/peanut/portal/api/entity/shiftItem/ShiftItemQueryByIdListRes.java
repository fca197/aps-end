package com.olivia.peanut.portal.api.entity.shiftItem;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

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
public class ShiftItemQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<ShiftItemDto> dataList;


}

