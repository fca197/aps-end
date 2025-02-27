package com.olivia.peanut.task.api.entity.taskDef;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 任务定义(TaskDef)查询对象返回
 *
 * @author makejava
 * @since 2025-02-26 16:17:47
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class TaskDefQueryByIdListRes {
  /***
   * 返回对象列表
   */
  private List<TaskDefDto> dataList;


}

