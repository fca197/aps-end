package com.olivia.peanut.portal.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.olivia.peanut.portal.model.ProcessLine;
import org.apache.ibatis.annotations.Mapper;

/**
 * 流水线信息(ProcessLine)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-11 10:55:21
 */
@Mapper
public interface ProcessLineMapper extends MPJBaseMapper<ProcessLine> {

}

