package com.olivia.peanut.base.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.olivia.peanut.base.model.BaseApp;
import org.apache.ibatis.annotations.Mapper;

/**
 * 应用表(BaseApp)表数据库访问层
 *
 * @author peanut
 * @since 2024-08-05 11:18:58
 */
@Mapper
public interface BaseAppMapper extends MPJBaseMapper<BaseApp> {

}

