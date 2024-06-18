package com.olivia.peanut.portal.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.olivia.peanut.portal.model.Brand;
import org.apache.ibatis.annotations.Mapper;

/**
 * 品牌信息(Brand)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-11 10:44:02
 */
@Mapper
public interface BrandMapper extends MPJBaseMapper<Brand> {

}

