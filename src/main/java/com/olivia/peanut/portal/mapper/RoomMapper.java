package com.olivia.peanut.portal.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.olivia.peanut.portal.model.Room;
import org.apache.ibatis.annotations.Mapper;

/**
 * 房间信息(Room)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-11 17:20:54
 */
@Mapper
public interface RoomMapper extends MPJBaseMapper<Room> {

}

