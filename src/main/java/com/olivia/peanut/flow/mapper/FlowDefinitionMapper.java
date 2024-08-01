package com.olivia.peanut.flow.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.olivia.peanut.flow.model.FlowDefinition;
import org.apache.ibatis.annotations.Mapper;

/**
 * 工作定义表(FlowDefinition)表数据库访问层
 *
 * @author peanut
 * @since 2024-08-01 10:43:49
 */
@Mapper
public interface FlowDefinitionMapper extends MPJBaseMapper<FlowDefinition> {

}

