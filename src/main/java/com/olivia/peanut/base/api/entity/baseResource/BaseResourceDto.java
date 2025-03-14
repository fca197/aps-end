package com.olivia.peanut.base.api.entity.baseResource;


import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 资源(BaseResource)查询对象返回
 *
 * @author peanut
 * @since 2024-08-06 17:29:01
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseResourceDto extends BaseEntityDto {

  /***
   *  菜单编码
   */
  @NotBlank(message = "菜单编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String resourceCode;
  /***
   *  菜单名称
   */
  @NotBlank(message = "菜单名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String resourceName;
  /***
   *  菜单URL
   */
//  @NotBlank(message = "菜单URL不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String resourceUrl;
  /***
   *  菜单图标
   */
//  @NotBlank(message = "菜单图标不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String resourceIcon;
  /***
   *  菜单类型
   */
//  @NotBlank(message = "菜单类型不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String resourceType;
  /***
   *  是否按钮 0 否,1 是
   */
  @NotNull(message = "是否按钮 0 否,1 是不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Boolean isButton;
  /***
   *  父菜单ID
   */
  @NotNull(message = "父菜单ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long parentId;
  /***
   *  菜单路径
   */
//  @NotBlank(message = "菜单路径不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String path;

}


