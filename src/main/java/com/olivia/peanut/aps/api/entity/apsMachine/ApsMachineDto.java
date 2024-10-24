package com.olivia.peanut.aps.api.entity.apsMachine;

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
 * aps 生产机器(ApsMachine)查询对象返回
 *
 * @author makejava
 * @since 2024-10-24 16:31:16
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsMachineDto extends BaseEntityDto {

  /***
   *  机器编号
   */
  @NotBlank(message = "机器编号不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String machineNo;
  /***
   *  机器名称
   */
  @NotBlank(message = "机器名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String machineName;

}


