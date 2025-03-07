package com.olivia.peanut.task.api.entity.taskInstanceHistory;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 任务实例历史(TaskInstanceHistory)保存返回
 *
 * @author makejava
 * @since 2025-03-06 13:27:08
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class TaskInstanceHistoryImportRes {
  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

