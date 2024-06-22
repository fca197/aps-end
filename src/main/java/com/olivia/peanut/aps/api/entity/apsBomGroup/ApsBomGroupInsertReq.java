package com.olivia.peanut.aps.api.entity.apsBomGroup;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 零件组配置(ApsBomGroup)保存入参
 *
 * @author peanut
 * @since 2024-06-19 17:41:23
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsBomGroupInsertReq extends ApsBomGroupDto {

  public void checkParam() {
  }
}

