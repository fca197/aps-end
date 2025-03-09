package com.olivia.peanut.task.model;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * 任务流程实例(TaskInstance)表实体类
 *
 * @author makejava
 * @since 2025-03-09 14:13:52
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("task_instance")
public class TaskInstance extends BaseEntity {
  /***
   *  任务id
   */
  private Long taskDefId;
  /***
   *  任务内容
   */
  private String instanceContent;
  /***
   *  执行状态
   */
  private String taskExecStatus;
  /***
   *  异常描述
   */
  private String exceptionMsg;
  /***
   *  耗时
   */
  private Integer useTime;
  /***
   *  次数
   */
  private Integer execLoop;
  /***
   *  检查次数
   */
  private Integer checkLoop;
  /***
   *  检查状态
   */
  private String checkExecStatus;
  /***
   *  检查异常信息
   */
  private String checkExceptionMsg;
  /***
   *  执行日期
   */
  private LocalDate execDate;

  @TableField(exist = false)
  private Long startMs;
}

