package com.olivia.peanut.base.api.entity.baseResource;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 资源(BaseResource)保存入参
 *
 * @author peanut
 * @since 2024-08-06 17:29:01
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseResourceInsertReq extends BaseResourceDto {

  public void checkParam() {
  }
}

