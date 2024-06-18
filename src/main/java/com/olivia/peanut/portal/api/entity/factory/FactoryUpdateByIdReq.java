package com.olivia.peanut.portal.api.entity.factory;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 工段信息(Factory)表实体类
 *
 * @author makejava
 * @since 2024-03-11 10:44:05
 */
@Accessors(chain = true)
@Getter
@Setter

public class FactoryUpdateByIdReq extends FactoryDto {


  public void checkParam() {
  }

}

