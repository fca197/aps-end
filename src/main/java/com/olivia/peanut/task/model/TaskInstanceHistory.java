package com.olivia.peanut.task.model;


import java.time.LocalDate;
import java.time.LocalDateTime;

import com.olivia.peanut.task.engine.entity.vo.TaskExecStatus;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 任务实例历史(TaskInstanceHistory)表实体类
 *
 * @author makejava
 * @since 2025-03-06 13:27:08
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("task_instance_history")
public class TaskInstanceHistory extends BaseEntity {
  /***
   *  实例ID
   */
  private Long instanceId;
  /***
   *  任务ID
   */
  private Long taskId;
  /***
   *  任务节点ID
   */
  private String taskDefId;
  /***
   *  入参
   */
  private String taskInput;
  /***
   *  返回值
   */
  private String taskOutput;
  /***
   *  执行状态
   */
  private TaskExecStatus taskExecStatus;
  /***
   *  异常描述
   */
  private String exceptionMsg;
  /***
   *  耗时
   */
  private Long useTime;

}

