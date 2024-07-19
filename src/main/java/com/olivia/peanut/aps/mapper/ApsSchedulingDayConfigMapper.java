package com.olivia.peanut.aps.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.olivia.peanut.aps.model.ApsSchedulingDayConfig;
import org.apache.ibatis.annotations.Mapper;

/**
 * 排程版本表(ApsSchedulingDayConfig)表数据库访问层
 *
 * @author peanut
 * @since 2024-07-19 15:05:00
 */
@Mapper
public interface ApsSchedulingDayConfigMapper extends MPJBaseMapper<ApsSchedulingDayConfig> {

}

