package com.olivia.peanut.base.api.entity.baseSupplier;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (BaseSupplier)查询对象入参
 *
 * @author peanut
 * @since 2024-03-28 15:35:37
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseSupplierQueryListReq {

  private BaseSupplierDto data;
}

