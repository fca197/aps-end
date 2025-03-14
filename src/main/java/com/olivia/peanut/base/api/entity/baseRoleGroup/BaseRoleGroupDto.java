package com.olivia.peanut.base.api.entity.baseRoleGroup;


import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 角色组表(BaseRoleGroup)查询对象返回
 *
 * @author peanut
 * @since 2024-07-31 14:33:37
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseRoleGroupDto extends BaseEntityDto {

  /***
   *  角色组编码
   */
  @NotBlank(message = "角色组编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String roleGroupCode;
  /***
   *  角色组名称
   */
  @NotBlank(message = "角色组名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String roleGroupName;

}


