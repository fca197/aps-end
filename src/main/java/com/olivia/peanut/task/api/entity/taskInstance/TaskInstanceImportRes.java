package com.olivia.peanut.task.api.entity.taskInstance;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 任务流程实例(TaskInstance)保存返回
 *
 * @author makejava
 * @since 2025-03-09 14:13:52
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class TaskInstanceImportRes {
  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

