package com.olivia.peanut.portal.api.entity.jcxOrder;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (JcxOrder)表实体类
 *
 * @author peanut
 * @since 2024-03-22 10:38:06
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class JcxOrderUpdateByIdReq extends JcxOrderDto {


  public void checkParam() {
  }

}

