package com.olivia.peanut.aps.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.olivia.peanut.aps.model.ApsBom;
import org.apache.ibatis.annotations.Mapper;

/**
 * BOM 清单(ApsBom)表数据库访问层
 *
 * @author peanut
 * @since 2024-06-06 11:27:34
 */
@Mapper
public interface ApsBomMapper extends MPJBaseMapper<ApsBom> {

}

