package com.olivia.peanut.aps.api.entity.apsProduceProcess;

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
 * aps 生产路径(ApsProduceProcess)查询对象返回
 *
 * @author makejava
 * @since 2024-10-24 17:00:20
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsProduceProcessDto extends BaseEntityDto {

  /***
   *  生产路径编码
   */
  @NotBlank(message = "生产路径编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String produceProcessNo;
  /***
   *  生产路径名称
   */
  @NotBlank(message = "生产路径名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String produceProcessName;

}


