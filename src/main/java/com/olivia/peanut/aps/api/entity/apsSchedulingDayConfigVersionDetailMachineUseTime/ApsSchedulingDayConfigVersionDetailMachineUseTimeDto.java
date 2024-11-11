package com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersionDetailMachineUseTime;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.alibaba.excel.annotation.ExcelProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 排程结果机器使用率(ApsSchedulingDayConfigVersionDetailMachineUseTime)查询对象返回
 *
 * @author makejava
 * @since 2024-11-11 15:21:50
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingDayConfigVersionDetailMachineUseTimeDto extends BaseEntityDto {

  /***
   *  排程ID
   */
  @NotNull(message = "排程ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long schedulingDayId;
  /***
   *  机器ID
   */
  @NotNull(message = "机器ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long machineId;
  /***
   *  耗时
   */
  @NotNull(message = "耗时不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long useTime;
  /***
   *  使用率
   */
  @NotNull(message = "使用率不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private BigDecimal useUsageRate;
  /***
   *  商品数
   */
  @NotNull(message = "商品数不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Integer makeProduceCount;

}


