package com.olivia.peanut.task.api.entity.taskDef;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 任务定义(TaskDef)查询对象入参
 *
 * @author makejava
 * @since 2025-02-26 16:17:47
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class TaskDefQueryByIdListReq {
  private List<Long> idList;

}

