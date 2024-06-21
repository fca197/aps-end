package com.olivia.peanut.blog.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.olivia.peanut.blog.model.BlogGroup;
import org.apache.ibatis.annotations.Mapper;

/**
 * 帖子组清单(BlogGroup)表数据库访问层
 *
 * @author peanut
 * @since 2024-06-21 13:23:43
 */
@Mapper
public interface BlogGroupMapper extends MPJBaseMapper<BlogGroup> {

}

