package com.olivia.peanut.portal.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.olivia.peanut.portal.model.Calendar;
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

