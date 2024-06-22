package com.olivia.peanut.blog.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
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
//@SuppressWarnings("serial")
@TableName("blog_group")
public class BlogGroup extends BaseEntity {

  /***
   *  组名称
   */
  private String groupName;
  /***
   *  组编码
   */
  private String groupCode;

}

