package com.olivia.peanut.aps.api.entity.apsOrderGoodsSaleConfig;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsOrderGoodsSaleConfig)保存入参
 *
 * @author peanut
 * @since 2024-04-09 13:19:37
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderGoodsSaleConfigInsertReq extends ApsOrderGoodsSaleConfigDto {

  public void checkParam() {
  }
}

