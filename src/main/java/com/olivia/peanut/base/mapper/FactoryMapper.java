package com.olivia.peanut.base.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.olivia.peanut.base.model.Factory;
import org.apache.ibatis.annotations.Mapper;

/**
 * 工段信息(Factory)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-11 10:44:05
 */
@Mapper
public interface FactoryMapper extends MPJBaseMapper<Factory> {

}

