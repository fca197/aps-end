package com.olivia.peanut.task.api.entity.taskInstanceHistory;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 任务实例历史(TaskInstanceHistory)根据ID删除多个入参
 *
 * @author makejava
 * @since 2025-03-06 13:27:07
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class TaskInstanceHistoryDeleteByIdListReq {
  /***
   * 要删除的ID
   */
  @NotEmpty(message = "请选择删除对象")
  private List<Long> idList;

}

