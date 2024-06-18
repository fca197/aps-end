package com.olivia.peanut.aps.api.entity.apsBom;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * BOM 清单(ApsBom)表实体类
 *
 * @author peanut
 * @since 2024-06-06 11:27:34
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsBomUpdateByIdReq extends ApsBomDto {


  public void checkParam() {
  }

}

