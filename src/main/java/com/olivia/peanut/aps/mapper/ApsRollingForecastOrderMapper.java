package com.olivia.peanut.aps.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.olivia.peanut.aps.model.ApsRollingForecastOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * 滚动预测(ApsRollingForecastOrder)表数据库访问层
 *
 * @author peanut
 * @since 2024-07-14 20:22:28
 */
@Mapper
public interface ApsRollingForecastOrderMapper extends MPJBaseMapper<ApsRollingForecastOrder> {

}

