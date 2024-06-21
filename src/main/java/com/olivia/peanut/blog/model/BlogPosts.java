package com.olivia.peanut.blog.model;


import java.time.LocalDate;
import java.time.LocalDateTime;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 帖子清单(BlogPosts)表实体类
 *
 * @author peanut
 * @since 2024-06-21 13:23:44
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("blog_posts")
public class BlogPosts extends BaseEntity {

  /***
   *  帖子组ID
   */
  private Long postsGroupId;
  /***
   *  帖子名称
   */
  private String postsName;
  /***
   *  帖子标签
   */
  private String postsTags;
  /***
   *  帖子编码
   */
  private String postsCode;
  /***
   *  帖子内容
   */
  private String postsContent;
  /***
   *  阅读数量
   */
  private Integer readNumber;
  /***
   *  是否置顶 0 否,1 是
   */
  private Boolean isTop;

}

