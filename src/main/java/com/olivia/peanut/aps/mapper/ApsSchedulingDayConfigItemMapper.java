package com.olivia.peanut.aps.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.olivia.peanut.aps.model.ApsSchedulingDayConfigItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * 排程版本配置表(ApsSchedulingDayConfigItem)表数据库访问层
 *
 * @author peanut
 * @since 2024-07-19 15:05:04
 */
@Mapper
public interface ApsSchedulingDayConfigItemMapper extends MPJBaseMapper<ApsSchedulingDayConfigItem> {

}

