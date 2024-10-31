package com.olivia.peanut.portal.api.entity.baseSupplier;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (BaseSupplier)查询对象返回
 *
 * @author peanut
 * @since 2024-03-28 15:35:37
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseSupplierQueryListRes {

  /***
   * 返回对象列表
   */
  private List<BaseSupplierDto> dataList;


}

