package com.olivia.peanut.blog.api.entity.blogGroup;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 帖子组清单(BlogGroup)查询对象入参
 *
 * @author peanut
 * @since 2024-06-21 13:23:44
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BlogGroupQueryByIdListReq {

  private List<Long> idList;


  public void checkParam() {
  }

}

