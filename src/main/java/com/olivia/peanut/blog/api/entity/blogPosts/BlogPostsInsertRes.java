package com.olivia.peanut.blog.api.entity.blogPosts;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 帖子清单(BlogPosts)保存返回
 *
 * @author peanut
 * @since 2024-06-21 13:23:44
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BlogPostsInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private Long id;
}

