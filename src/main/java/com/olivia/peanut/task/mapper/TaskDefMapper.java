package com.olivia.peanut.task.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.olivia.peanut.task.model.TaskDef;
import org.apache.ibatis.annotations.Mapper;

/**
 * 任务定义(TaskDef)表数据库访问层
 *
 * @author makejava
 * @since 2025-02-26 16:17:46
 */
@Mapper
public interface TaskDefMapper extends MPJBaseMapper<TaskDef> {

}

