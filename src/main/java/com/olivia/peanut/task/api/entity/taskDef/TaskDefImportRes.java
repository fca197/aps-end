package com.olivia.peanut.task.api.entity.taskDef;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 任务定义(TaskDef)保存返回
 *
 * @author makejava
 * @since 2025-02-26 16:17:46
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class TaskDefImportRes {
  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

