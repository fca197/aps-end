package com.olivia.peanut.blog.api.entity.blogPosts;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 帖子清单(BlogPosts)查询对象入参
 *
 * @author peanut
 * @since 2024-06-21 13:23:45
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BlogPostsQueryByIdListReq {

  private List<Long> idList;


  public void checkParam() {
  }

}

