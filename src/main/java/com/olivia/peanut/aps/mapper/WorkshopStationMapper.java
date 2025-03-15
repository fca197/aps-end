package com.olivia.peanut.aps.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.olivia.peanut.aps.model.ApsWorkshopStation;
import org.apache.ibatis.annotations.Mapper;

/**
 * 工位信息(WorkshopStation)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-11 10:55:23
 */
@Mapper
public interface WorkshopStationMapper extends MPJBaseMapper<ApsWorkshopStation> {

}

