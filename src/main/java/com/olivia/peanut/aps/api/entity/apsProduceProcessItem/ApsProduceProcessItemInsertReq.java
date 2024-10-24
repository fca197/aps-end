package com.olivia.peanut.aps.api.entity.apsProduceProcessItem;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * aps 生产机器(ApsProduceProcessItem)保存入参
 *
 * @author makejava
 * @since 2024-10-24 17:00:20
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsProduceProcessItemInsertReq extends ApsProduceProcessItemDto {

  public void checkParam() {
  }
}

