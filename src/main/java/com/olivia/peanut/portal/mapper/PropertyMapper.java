package com.olivia.peanut.portal.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.olivia.peanut.portal.model.Property;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资产信息(Property)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-11 17:20:53
 */
@Mapper
public interface PropertyMapper extends MPJBaseMapper<Property> {

}

