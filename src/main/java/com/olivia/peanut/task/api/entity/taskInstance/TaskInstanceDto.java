package com.olivia.peanut.task.api.entity.taskInstance;

import java.time.LocalDate;
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
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.fastjson2.annotation.JSONField;
import com.olivia.sdk.utils.fastjson.Boolean2StrFeature;
import com.olivia.sdk.utils.fastjson.Str2BooleanConverter;

/**
 * 任务流程实例(TaskInstance)查询对象返回
 *
 * @author makejava
 * @since 2025-03-09 14:13:53
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class TaskInstanceDto extends BaseEntityDto {

  /***
   *  任务id
   */
  @JSONField(label = "taskDefId")
  @NotNull(message = "任务id不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private Long taskDefId;
  /***
   *  任务内容
   */
  @NotBlank(message = "任务内容不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  @JSONField(label = "instanceContent")

  private String instanceContent;
  /***
   *  执行状态
   */
  @NotBlank(message = "执行状态 不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  @JSONField(label = "taskExecStatus")

  private String taskExecStatus;
  /***
   *  异常描述
   */
  @NotBlank(message = "异常描述不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  @JSONField(label = "exceptionMsg")

  private String exceptionMsg;
  /***
   *  耗时
   */
  @JSONField(label = "useTime")
  @NotNull(message = "耗时不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private Integer useTime;
  /***
   *  次数
   */
  @JSONField(label = "execLoop")
  @NotNull(message = "次数不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private Integer execLoop;
  /***
   *  检查次数
   */
  @JSONField(label = "checkLoop")
  @NotNull(message = "检查次数不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private Integer checkLoop;
  /***
   *  检查状态
   */
  @NotBlank(message = "检查状态不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  @JSONField(label = "checkExecStatus")

  private String checkExecStatus;
  /***
   *  检查异常信息
   */
  @NotBlank(message = "检查异常信息不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  @JSONField(label = "checkExceptionMsg")

  private String checkExceptionMsg;
  /***
   *  执行日期
   */
  @JSONField(label = "execDate")
  @NotNull(message = "执行日期不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private LocalDate execDate;

}


