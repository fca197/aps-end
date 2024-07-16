package com.olivia.peanut.aps.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.olivia.peanut.aps.model.ApsRollingForecastOrderItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * 滚动预测订单节点表(ApsRollingForecastOrderItem)表数据库访问层
 *
 * @author peanut
 * @since 2024-07-16 10:31:19
 */
@Mapper
public interface ApsRollingForecastOrderItemMapper extends MPJBaseMapper<ApsRollingForecastOrderItem> {

}

