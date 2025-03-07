package com.olivia.peanut.task.api.entity.taskInstanceHistory;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 任务实例历史(TaskInstanceHistory)查询对象入参
 *
 * @author makejava
 * @since 2025-03-06 13:27:08
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class TaskInstanceHistoryQueryByIdListReq {
  private List<Long> idList;

}

