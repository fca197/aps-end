package com.olivia.peanut.base.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.olivia.peanut.base.model.Dictionary;
import org.apache.ibatis.annotations.Mapper;

/**
 * 字典值(Dictionary)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-11 10:44:04
 */
@Mapper
public interface DictionaryMapper extends MPJBaseMapper<Dictionary> {

}

