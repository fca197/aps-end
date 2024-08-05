package com.olivia.peanut.base.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.olivia.peanut.base.model.BaseAppResource;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资源(BaseAppResource)表数据库访问层
 *
 * @author peanut
 * @since 2024-08-05 11:19:00
 */
@Mapper
public interface BaseAppResourceMapper extends MPJBaseMapper<BaseAppResource> {

}
