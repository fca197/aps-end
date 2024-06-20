package com.olivia.peanut.aps.api.entity.apsGoodsBomGroup;

import java.time.LocalDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 零件组配置(ApsGoodsBomGroup)表实体类
 *
 * @author peanut
 * @since 2024-06-19 16:49:04
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsBomGroupUpdateByIdReq extends ApsGoodsBomGroupDto {


  public void checkParam() {
  }

}

