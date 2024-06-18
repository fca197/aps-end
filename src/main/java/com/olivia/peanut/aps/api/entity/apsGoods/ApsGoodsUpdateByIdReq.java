package com.olivia.peanut.aps.api.entity.apsGoods;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoods)表实体类
 *
 * @author peanut
 * @since 2024-03-29 16:11:23
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsUpdateByIdReq extends ApsGoodsDto {


  public void checkParam() {
  }

}

