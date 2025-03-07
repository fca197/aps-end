package com.olivia.peanut.task.api.entity.taskInstanceHistory;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 任务实例历史(TaskInstanceHistory)根据ID删除多个反参
 *
 * @author makejava
 * @since 2025-03-06 13:27:07
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class TaskInstanceHistoryDeleteByIdListRes {
  /***
   * 受影响行数
   */
  private int count;

}

