package com.olivia.peanut.aps.api.entity.apsProduceProcessItem;

import java.time.LocalDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * aps 生产机器(ApsProduceProcessItem)表实体类
 *
 * @author makejava
 * @since 2024-10-24 17:00:21
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsProduceProcessItemUpdateByIdReq extends ApsProduceProcessItemDto {


  public void checkParam() {
  }

}

