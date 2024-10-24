package com.olivia.peanut.aps.api.entity.apsProduceProcessItem;

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
 * aps 生产机器(ApsProduceProcessItem)查询对象返回
 *
 * @author makejava
 * @since 2024-10-24 17:00:22
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsProduceProcessItemDto extends BaseEntityDto {

  /***
   *  生产路径 Id aps_produce_process
   */
  @NotNull(message = "生产路径 Id aps_produce_process不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long produceProcessId;
  /***
   *  机器ID
   */
  @NotNull(message = "机器ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long machineId;
  /***
   *  耗时（秒）
   */
  @NotNull(message = "耗时（秒）不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long machineUseTimeSecond;

}


