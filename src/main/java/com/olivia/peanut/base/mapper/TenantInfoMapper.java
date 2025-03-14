package com.olivia.peanut.base.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.olivia.peanut.base.model.TenantInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 租户信息(TenantInfo)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-11 10:55:22
 */
@Mapper
public interface TenantInfoMapper extends MPJBaseMapper<TenantInfo> {

}

