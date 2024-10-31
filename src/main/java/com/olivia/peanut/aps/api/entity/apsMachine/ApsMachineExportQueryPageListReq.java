package com.olivia.peanut.aps.api.entity.apsMachine;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * aps 生产机器(ApsMachine)查询对象入参
 *
 * @author makejava
 * @since 2024-10-24 16:31:15
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsMachineExportQueryPageListReq {
  private int pageNum;
  private int pageSize;
  private Boolean queryPage = true;
  private ApsMachineDto data;


  public void checkParam() {
  }

}

