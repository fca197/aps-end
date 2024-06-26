package com.olivia.peanut.blog.api.entity.blogGroup;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 帖子组清单(BlogGroup)表实体类
 *
 * @author peanut
 * @since 2024-06-21 13:23:43
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BlogGroupUpdateByIdReq extends BlogGroupDto {


  public void checkParam() {
  }

}
