package com.olivia.peanut.task.api.entity.taskInstance;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 任务流程实例(TaskInstance)根据ID删除多个反参
 *
 * @author makejava
 * @since 2025-03-09 14:13:51
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class TaskInstanceDeleteByIdListRes {
  /***
   * 受影响行数
   */
  private int count;

}

