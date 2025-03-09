package com.olivia.peanut.task.api.entity.taskInstance;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 任务流程实例(TaskInstance)查询对象返回
 *
 * @author makejava
 * @since 2025-03-09 14:13:51
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class TaskInstanceQueryListRes {
  /***
   * 返回对象列表
   */
  private List<TaskInstanceDto> dataList;


}

