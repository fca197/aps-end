package com.olivia.peanut.task.api.entity.taskInstanceHistory;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 任务实例历史(TaskInstanceHistory)保存返回
 *
 * @author makejava
 * @since 2025-03-06 13:27:07
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class TaskInstanceHistoryInsertRes {
  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

