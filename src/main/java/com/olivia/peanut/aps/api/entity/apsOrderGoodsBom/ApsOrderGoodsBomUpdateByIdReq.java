package com.olivia.peanut.aps.api.entity.apsOrderGoodsBom;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 订单商品零件表(ApsOrderGoodsBom)表实体类
 *
 * @author peanut
 * @since 2024-07-30 10:28:23
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderGoodsBomUpdateByIdReq extends ApsOrderGoodsBomDto {


  public void checkParam() {
  }

}
