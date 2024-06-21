package com.olivia.peanut.blog.api.entity.blogPosts;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 帖子清单(BlogPosts)查询对象返回
 *
 * @author peanut
 * @since 2024-06-21 13:23:45
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BlogPostsDto extends BaseEntityDto {

  /***
   *  帖子组ID
   */
  @NotNull(message = "帖子组ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long postsGroupId;
  private String postsGroupName;
  /***
   *  帖子名称
   */
  @NotBlank(message = "帖子名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String postsName;
  /***
   *  帖子标签
   */
  @NotBlank(message = "帖子标签不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String postsTags;
  /***
   *  帖子编码
   */
  @NotBlank(message = "帖子编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String postsCode;
  /***
   *  帖子内容
   */
  @NotBlank(message = "帖子内容不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String postsContent;
  /***
   *  阅读数量
   */
  @NotNull(message = "阅读数量不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Integer readNumber;
  /***
   *  是否置顶 0 否,1 是
   */
  @NotNull(message = "是否置顶 0 否,1 是不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Boolean isTop;

  public String getTop() {
    return Boolean.TRUE.equals(this.isTop) ? "是" : "否";
  }
}


