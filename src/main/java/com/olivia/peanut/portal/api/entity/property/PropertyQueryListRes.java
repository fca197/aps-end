package com.olivia.peanut.portal.api.entity.property;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 资产信息(Property)查询对象返回
 *
 * @author makejava
 * @since 2024-03-11 17:20:52
 */
@Accessors(chain = true)
@Getter
@Setter

public class PropertyQueryListRes {

  /***
   * 返回对象列表
   */
  private List<PropertyDto> dataList;


}

