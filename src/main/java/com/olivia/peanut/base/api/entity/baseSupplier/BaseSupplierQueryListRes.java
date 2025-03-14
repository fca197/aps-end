package com.olivia.peanut.base.api.entity.baseSupplier;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

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

