package com.olivia.peanut.aps.api.entity.apsProduceProcess;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * aps 生产路径(ApsProduceProcess)查询对象入参
 *
 * @author makejava
 * @since 2024-10-24 17:00:19
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsProduceProcessExportQueryPageListReq {
  private int pageNum;
  private int pageSize;
  private Boolean queryPage = true;
  private ApsProduceProcessDto data;


  public void checkParam() {
  }

}

