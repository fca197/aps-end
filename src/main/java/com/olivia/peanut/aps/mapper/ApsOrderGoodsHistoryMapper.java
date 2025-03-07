package com.olivia.peanut.aps.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.olivia.peanut.aps.model.ApsOrderGoodsHistory;
import org.apache.ibatis.annotations.Mapper;

/**
 * 历史订单记录(ApsOrderGoodsHistory)表数据库访问层
 *
 * @author makejava
 * @since 2025-02-20 17:18:46
 */
@Mapper
public interface ApsOrderGoodsHistoryMapper extends MPJBaseMapper<ApsOrderGoodsHistory> {

}

