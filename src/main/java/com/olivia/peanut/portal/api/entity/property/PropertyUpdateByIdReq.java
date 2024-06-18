package com.olivia.peanut.portal.api.entity.property;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 资产信息(Property)表实体类
 *
 * @author makejava
 * @since 2024-03-11 17:20:52
 */
@Accessors(chain = true)
@Getter
@Setter

public class PropertyUpdateByIdReq extends PropertyDto {


  public void checkParam() {
  }

}

