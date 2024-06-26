package com.olivia.peanut.blog.api.entity.blogGroup;

import lombok.Getter;
import lombok.Setter;

/**
 * 帖子组清单(BlogGroup)查询对象返回
 *
 * @author peanut
 * @since 2024-06-21 13:23:43
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BlogGroupImportReq extends BlogGroupDto {


  public void checkParam() {
  }

}

