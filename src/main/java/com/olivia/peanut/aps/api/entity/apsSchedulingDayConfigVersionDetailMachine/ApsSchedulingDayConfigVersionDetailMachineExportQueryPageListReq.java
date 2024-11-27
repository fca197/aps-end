package com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersionDetailMachine;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 排程版本详情_机器(ApsSchedulingDayConfigVersionDetailMachine)查询对象入参
 *
 * @author makejava
 * @since 2024-10-27 00:12:54
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingDayConfigVersionDetailMachineExportQueryPageListReq {
  private int pageNum;
  private int pageSize;
  private Boolean queryPage = true;
  private ApsSchedulingDayConfigVersionDetailMachineDto data;

  /***
   * 默认 间隔 5分钟
   */
  private Long timeSpan = 60 * 5L;


}

