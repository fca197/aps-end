package com.olivia.peanut.task.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.olivia.peanut.task.model.TaskInstanceHistory;
import org.apache.ibatis.annotations.Mapper;

/**
 * 任务实例历史(TaskInstanceHistory)表数据库访问层
 *
 * @author makejava
 * @since 2025-03-06 13:27:08
 */
@Mapper
public interface TaskInstanceHistoryMapper extends MPJBaseMapper<TaskInstanceHistory> {

}

