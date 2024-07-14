package com.olivia.peanut.aps.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.olivia.peanut.aps.model.ApsOrderRollingForecast;
import org.apache.ibatis.annotations.Mapper;

/**
 * 滚动预测(ApsOrderRollingForecast)表数据库访问层
 *
 * @author peanut
 * @since 2024-07-14 19:43:51
 */
@Mapper
public interface ApsOrderRollingForecastMapper extends MPJBaseMapper<ApsOrderRollingForecast> {

}

