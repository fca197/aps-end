package com.olivia.peanut.blog.api.entity.blogGroup;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 帖子组清单(BlogGroup)保存返回
 *
 * @author peanut
 * @since 2024-06-21 13:23:43
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BlogGroupImportRes {

  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

