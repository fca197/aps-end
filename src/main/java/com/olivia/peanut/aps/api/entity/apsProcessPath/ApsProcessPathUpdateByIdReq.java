package com.olivia.peanut.aps.api.entity.apsProcessPath;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsProcessPath)表实体类
 *
 * @author peanut
 * @since 2024-04-01 17:49:18
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsProcessPathUpdateByIdReq extends ApsProcessPathDto {


  public void checkParam() {
  }

}

