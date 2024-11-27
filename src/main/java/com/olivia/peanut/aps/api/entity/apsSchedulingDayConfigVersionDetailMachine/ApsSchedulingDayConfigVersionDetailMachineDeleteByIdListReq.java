package com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersionDetailMachine;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 排程版本详情_机器(ApsSchedulingDayConfigVersionDetailMachine)根据ID删除多个入参
 *
 * @author makejava
 * @since 2024-10-27 00:12:54
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingDayConfigVersionDetailMachineDeleteByIdListReq {
  /***
   * 要删除的ID
   */
  @NotEmpty(message = "请选择删除对象")
  private List<Long> idList;


}

