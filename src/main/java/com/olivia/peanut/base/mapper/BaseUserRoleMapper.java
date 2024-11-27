package com.olivia.peanut.base.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.olivia.peanut.base.model.BaseUserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户角色表(BaseUserRole)表数据库访问层
 *
 * @author peanut
 * @since 2024-07-31 14:36:03
 */
@Mapper
public interface BaseUserRoleMapper extends MPJBaseMapper<BaseUserRole> {

}

