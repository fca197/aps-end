package com.olivia.peanut.aps.api.entity.apsProduceProcess;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * aps 生产路径(ApsProduceProcess)查询对象入参
 *
 * @author makejava
 * @since 2024-10-24 17:00:20
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsProduceProcessQueryByIdListReq {
  private List<Long> idList;


  public void checkParam() {
  }

}

