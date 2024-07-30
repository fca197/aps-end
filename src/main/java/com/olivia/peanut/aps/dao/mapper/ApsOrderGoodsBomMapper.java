package com.olivia.peanut.aps.dao.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.olivia.peanut.aps.model.ApsOrderGoodsBom;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单商品零件表(ApsOrderGoodsBom)表数据库访问层
 *
 * @author peanut
 * @since 2024-07-30 10:27:17
 */
@Mapper
public interface ApsOrderGoodsBomMapper extends MPJBaseMapper<ApsOrderGoodsBom> {

}

