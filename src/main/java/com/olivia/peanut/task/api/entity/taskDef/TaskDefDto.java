package com.olivia.peanut.task.api.entity.taskDef;

import com.alibaba.fastjson2.annotation.JSONField;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 任务定义(TaskDef)查询对象返回
 *
 * @author makejava
 * @since 2025-02-26 16:17:47
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class TaskDefDto extends BaseEntityDto {

  /***
   *  任务名称
   */
  @NotBlank(message = "任务名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  @JSONField(label = "taskName")

  private String taskName;
  /***
   *  任务编号
   */
  @NotBlank(message = "任务编号不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  @JSONField(label = "tasCode")

  private String tasCode;
  /***
   *  任务备注
   */
  @NotBlank(message = "任务备注不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  @JSONField(label = "taskRemark")

  private String taskRemark;
  /***
   *  任务名称
   */
  @NotBlank(message = "任务名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  @JSONField(label = "taskDefContent")

  private String taskDefContent;

}


