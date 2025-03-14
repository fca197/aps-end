package com.olivia.peanut.base.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.olivia.peanut.base.model.Calendar;
import org.apache.ibatis.annotations.Mapper;

/**
 * 工作日历(Calendar)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-11 10:44:03
 */
@Mapper
public interface CalendarMapper extends MPJBaseMapper<Calendar> {

}

