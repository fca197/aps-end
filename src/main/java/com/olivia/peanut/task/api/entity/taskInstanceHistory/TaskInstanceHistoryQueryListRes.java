package com.olivia.peanut.task.api.entity.taskInstanceHistory;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 任务实例历史(TaskInstanceHistory)查询对象返回
 *
 * @author makejava
 * @since 2025-03-06 13:27:07
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class TaskInstanceHistoryQueryListRes {
  /***
   * 返回对象列表
   */
  private List<TaskInstanceHistoryDto> dataList;


}

