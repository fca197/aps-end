package com.olivia.peanut.base.api.entity.baseRoleGroupResource;


import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 角色组资源表(BaseRoleGroupResource)查询对象返回
 *
 * @author peanut
 * @since 2024-08-09 15:42:35
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseRoleGroupResourceDto extends BaseEntityDto {

  /***
   *  角色ID
   */
  @NotNull(message = "角色ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long roleGroupId;
  /***
   *  资源ID
   */
//  @NotNull(message = "资源ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long resourceId;

  private String resourceName;
  //  @NotNull(message = "资源ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private List<Long> resourceIdList;
}


