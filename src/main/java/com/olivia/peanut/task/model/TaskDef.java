package com.olivia.peanut.task.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 任务定义(TaskDef)表实体类
 *
 * @author makejava
 * @since 2025-02-26 16:17:46
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("task_def")
public class TaskDef extends BaseEntity {
  /***
   *  任务名称
   */
  private String taskName;
  /***
   *  任务编号
   */
  private String tasCode;
  /***
   *  任务备注
   */
  private String taskRemark;
  /***
   *  任务名称
   */
  private String taskDefContent;

}

