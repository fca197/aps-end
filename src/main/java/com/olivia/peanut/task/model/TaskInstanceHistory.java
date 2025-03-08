package com.olivia.peanut.task.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.peanut.task.engine.entity.vo.TaskExecStatus;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

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

  private String taskName;
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

  /****
   * 执行次数
   */
  private Integer execLoop;

  /***
   * 检查次数
   */
  private Long checkLoop;
  /****
   * 检查状态
   */
  private TaskExecStatus checkExecStatus;
  /****
   * 检查异常信息
   */
  private String checkExceptionMsg;

}

