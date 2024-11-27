package com.olivia.peanut.flow.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.olivia.peanut.flow.model.FlowGroup;
import org.apache.ibatis.annotations.Mapper;

/**
 * 工作流组表(FlowGroup)表数据库访问层
 *
 * @author peanut
 * @since 2024-08-01 10:43:53
 */
@Mapper
public interface FlowGroupMapper extends MPJBaseMapper<FlowGroup> {

}

