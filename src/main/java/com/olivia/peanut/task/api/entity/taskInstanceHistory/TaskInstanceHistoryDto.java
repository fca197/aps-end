package com.olivia.peanut.task.api.entity.taskInstanceHistory;

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
 * 任务实例历史(TaskInstanceHistory)查询对象返回
 *
 * @author makejava
 * @since 2025-03-06 13:27:08
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class TaskInstanceHistoryDto extends BaseEntityDto {

  /***
   *  实例ID
   */
  @JSONField(label = "instanceId")
  @NotNull(message = "实例ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private Long instanceId;
  /***
   *  任务ID
   */
  @JSONField(label = "taskId")
  @NotNull(message = "任务ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})

  private Long taskId;
  /***
   *  任务节点ID
   */
  @NotBlank(message = "任务节点ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  @JSONField(label = "taskDefId")

  private String taskDefId;
  /***
   *  入参
   */
  @NotBlank(message = "入参不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  @JSONField(label = "taskInput")

  private String taskInput;
  /***
   *  返回值
   */
  @NotBlank(message = "返回值不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  @JSONField(label = "taskOutput")

  private String taskOutput;
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

  private Long useTime;

}


