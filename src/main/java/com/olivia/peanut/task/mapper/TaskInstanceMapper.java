package com.olivia.peanut.task.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.olivia.peanut.task.model.TaskInstance;
import org.apache.ibatis.annotations.Mapper;

/**
 * 任务流程实例(TaskInstance)表数据库访问层
 *
 * @author makejava
 * @since 2025-03-09 14:13:52
 */
@Mapper
public interface TaskInstanceMapper extends MPJBaseMapper<TaskInstance> {

}

