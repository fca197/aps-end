package com.olivia.peanut.blog.api.entity.blogPosts;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 帖子清单(BlogPosts)查询对象返回
 *
 * @author peanut
 * @since 2024-06-21 13:23:44
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BlogPostsExportQueryPageListInfoRes extends BlogPostsDto {

}

