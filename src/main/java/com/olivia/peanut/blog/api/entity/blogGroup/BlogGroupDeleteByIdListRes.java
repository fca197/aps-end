package com.olivia.peanut.blog.api.entity.blogGroup;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 帖子组清单(BlogGroup)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-06-21 13:23:43
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BlogGroupDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

