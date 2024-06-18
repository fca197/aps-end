package com.olivia.peanut.portal.api.entity.baseSupplier;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (BaseSupplier)表实体类
 *
 * @author peanut
 * @since 2024-03-28 15:35:37
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseSupplierUpdateByIdReq extends BaseSupplierDto {


  public void checkParam() {
  }

}

