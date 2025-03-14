package com.olivia.peanut.base.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.olivia.peanut.base.model.MsgMessage;
import org.apache.ibatis.annotations.Mapper;

/**
 * (MsgMessage)表数据库访问层
 *
 * @author peanut
 * @since 2024-03-23 19:05:39
 */
@Mapper
public interface MsgMessageMapper extends MPJBaseMapper<MsgMessage> {

}

