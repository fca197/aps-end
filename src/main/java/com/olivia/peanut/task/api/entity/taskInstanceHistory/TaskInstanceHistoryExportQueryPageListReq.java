package com.olivia.peanut.task.api.entity.taskInstanceHistory;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 任务实例历史(TaskInstanceHistory)查询对象入参
 *
 * @author makejava
 * @since 2025-03-06 13:27:07
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class TaskInstanceHistoryExportQueryPageListReq {
  private int pageNum;
  private int pageSize;
  private Boolean queryPage = true;
  private TaskInstanceHistoryDto data;
}

