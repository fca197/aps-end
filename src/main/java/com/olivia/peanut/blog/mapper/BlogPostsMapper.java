package com.olivia.peanut.blog.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.olivia.peanut.blog.model.BlogPosts;
import org.apache.ibatis.annotations.Mapper;

/**
 * 帖子清单(BlogPosts)表数据库访问层
 *
 * @author peanut
 * @since 2024-06-21 13:23:44
 */
@Mapper
public interface BlogPostsMapper extends MPJBaseMapper<BlogPosts> {

}

