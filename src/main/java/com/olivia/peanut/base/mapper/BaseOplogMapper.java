package com.olivia.peanut.base.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.olivia.peanut.base.model.BaseOplog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 操作日志(BaseOplog)表数据库访问层
 *
 * @author makejava
 * @since 2024-11-30 16:01:02
 */
@Mapper
public interface BaseOplogMapper extends MPJBaseMapper<BaseOplog> {

}

