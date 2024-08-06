package com.olivia.peanut.base.api.entity.baseAppResource;

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
 * 资源(BaseAppResource)查询对象返回
 *
 * @author peanut
 * @since 2024-08-06 17:30:28
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseAppResourceDto extends BaseEntityDto {

  /***
   *  应用ID
   */
  @NotNull(message = "应用ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long appId;
  /***
   *  资源ID
   */
  @NotNull(message = "资源ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long resourceId;
  /***
   *  是否按钮 0 否,1 是
   */
  @NotNull(message = "是否按钮 0 否,1 是不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Boolean isButton;
  /***
   *  是否隐藏 0 否,1 是
   */
  @NotNull(message = "是否隐藏 0 否,1 是不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Boolean isHidden;
  /***
   *  文件路径
   */
  @NotBlank(message = "文件路径不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String filePath;
  /***
   *  父菜单ID
   */
  @NotNull(message = "父菜单ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long parentId;
  /***
   *  菜单路径
   */
  @NotBlank(message = "菜单路径不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String path;

}


