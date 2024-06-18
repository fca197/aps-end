package com.olivia.peanut.portal.api.entity.brand;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 品牌信息(Brand)表实体类
 *
 * @author makejava
 * @since 2024-03-11 10:44:02
 */
@Accessors(chain = true)
@Getter
@Setter

public class BrandUpdateByIdReq extends BrandDto {


  public void checkParam() {
  }

}

