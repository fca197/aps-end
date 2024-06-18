package com.olivia.peanut.portal.api.entity.property;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 资产信息(Property)查询对象返回
 *
 * @author makejava
 * @since 2024-03-11 17:20:54
 */
@Accessors(chain = true)
@Getter
@Setter

public class PropertyQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<PropertyDto> dataList;


}

