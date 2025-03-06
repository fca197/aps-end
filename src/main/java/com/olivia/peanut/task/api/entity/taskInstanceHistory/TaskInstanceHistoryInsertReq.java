package com.olivia.peanut.task.api.entity.taskInstanceHistory;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 任务实例历史(TaskInstanceHistory)保存入参
 *
 * @author makejava
 * @since 2025-03-06 13:27:07
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class TaskInstanceHistoryInsertReq extends TaskInstanceHistoryDto {

  public void checkParam() {
  }
}

