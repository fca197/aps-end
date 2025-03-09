package com.olivia.peanut.task.api.entity.taskInstance;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 任务流程实例(TaskInstance)根据ID删除多个入参
 *
 * @author makejava
 * @since 2025-03-09 14:13:51
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class TaskInstanceDeleteByIdListReq {
  /***
   * 要删除的ID
   */
  @NotEmpty(message = "请选择删除对象")
  private List<Long> idList;

}

