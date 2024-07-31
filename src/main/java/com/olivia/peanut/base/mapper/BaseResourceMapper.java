package com.olivia.peanut.base.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.olivia.peanut.base.model.BaseResource;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资源(BaseResource)表数据库访问层
 *
 * @author peanut
 * @since 2024-07-31 14:33:33
 */
@Mapper
public interface BaseResourceMapper extends MPJBaseMapper<BaseResource> {

}
