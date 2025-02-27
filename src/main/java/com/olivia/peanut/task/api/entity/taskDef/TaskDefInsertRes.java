package com.olivia.peanut.task.api.entity.taskDef;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 任务定义(TaskDef)保存返回
 *
 * @author makejava
 * @since 2025-02-26 16:17:43
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class TaskDefInsertRes {
  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

