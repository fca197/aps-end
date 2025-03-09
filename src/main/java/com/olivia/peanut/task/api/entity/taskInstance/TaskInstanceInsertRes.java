package com.olivia.peanut.task.api.entity.taskInstance;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 任务流程实例(TaskInstance)保存返回
 *
 * @author makejava
 * @since 2025-03-09 14:13:51
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class TaskInstanceInsertRes {
  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

