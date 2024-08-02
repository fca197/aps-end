package com.olivia.peanut.flow.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.olivia.peanut.flow.model.FlowFormItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * 工作流表单项表(FlowFormItem)表数据库访问层
 *
 * @author peanut
 * @since 2024-08-02 23:26:26
 */
@Mapper
public interface FlowFormItemMapper extends MPJBaseMapper<FlowFormItem> {

}

