package com.olivia.peanut.blog.api.entity.blogGroup;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.alibaba.excel.annotation.ExcelProperty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 帖子组清单(BlogGroup)查询对象返回
 *
 * @author peanut
 * @since 2024-06-21 13:23:44
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BlogGroupDto extends BaseEntityDto {

  /***
   *  组名称
   */
  @NotBlank(message = "组名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String groupName;
  /***
   *  组编码
   */
  @NotBlank(message = "组编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String groupCode;
}


