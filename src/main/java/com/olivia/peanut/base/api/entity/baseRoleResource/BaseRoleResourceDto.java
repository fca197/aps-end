package com.olivia.peanut.base.api.entity.baseRoleResource;


import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 角色资源表(BaseRoleResource)查询对象返回
 *
 * @author peanut
 * @since 2024-08-09 15:42:37
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseRoleResourceDto extends BaseEntityDto {

  /***
   *  角色ID
   */
  @NotNull(message = "角色ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long roleId;
  /***
   *  菜单ID
   */
//  @NotNull(message = "菜单ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long resourceId;

  private String resourceName;


  @NotNull(message = "资源ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private List<Long> resourceIdList;
}


