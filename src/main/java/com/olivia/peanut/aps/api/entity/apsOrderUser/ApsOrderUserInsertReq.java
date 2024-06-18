package com.olivia.peanut.aps.api.entity.apsOrderUser;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsOrderUser)保存入参
 *
 * @author peanut
 * @since 2024-04-09 13:19:38
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderUserInsertReq extends ApsOrderUserDto {

  public void checkParam() {
  }
}

