package com.olivia.peanut.task.api.entity.taskInstance;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 任务流程实例(TaskInstance)查询对象入参
 *
 * @author makejava
 * @since 2025-03-09 14:13:52
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class TaskInstanceQueryByIdListReq {
  private List<Long> idList;

}

