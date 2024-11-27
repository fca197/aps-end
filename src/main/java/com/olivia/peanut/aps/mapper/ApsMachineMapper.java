package com.olivia.peanut.aps.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.olivia.peanut.aps.model.ApsMachine;
import org.apache.ibatis.annotations.Mapper;

/**
 * aps 生产机器(ApsMachine)表数据库访问层
 *
 * @author makejava
 * @since 2024-10-24 16:31:15
 */
@Mapper
public interface ApsMachineMapper extends MPJBaseMapper<ApsMachine> {

}

