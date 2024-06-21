package com.olivia.peanut.blog.api.entity.blogPosts;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 帖子清单(BlogPosts)保存入参
 *
 * @author peanut
 * @since 2024-06-21 13:23:44
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BlogPostsInsertReq extends BlogPostsDto {

  public void checkParam() {
  }
}

