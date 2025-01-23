package com.olivia.peanut.flow.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.olivia.peanut.flow.model.FlowForm;
import org.apache.ibatis.annotations.Mapper;

/**
 * 工作流表单表(FlowForm)表数据库访问层
 *
 * @author peanut
 * @since 2024-08-02 23:26:22
 */
@Mapper
public interface FlowFormMapper extends MPJBaseMapper<FlowForm> {

}

