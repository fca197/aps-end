package com.olivia.peanut.base.api.entity.baseRole;


import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 角色表(BaseRole)查询对象返回
 *
 * @author peanut
 * @since 2024-07-31 14:33:35
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseRoleDto extends BaseEntityDto {

  /***
   *  角色编码
   */
  @NotBlank(message = "角色编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String roleCode;
  /***
   *  角色名称
   */
  @NotBlank(message = "角色名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String roleName;
  /***
   *  角色组
   */
//  @NotNull(message = "角色组不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long roleGroupId;

  private String roleGroupName;
}


