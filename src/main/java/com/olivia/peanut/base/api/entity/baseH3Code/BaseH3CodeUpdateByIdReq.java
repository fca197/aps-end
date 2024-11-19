package com.olivia.peanut.base.api.entity.baseH3Code;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * H3对应的值(BaseH3Code)表实体类
 *
 * @author makejava
 * @since 2024-11-19 16:09:18
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseH3CodeUpdateByIdReq extends BaseH3CodeDto {


  public void checkParam() {
  }

}

