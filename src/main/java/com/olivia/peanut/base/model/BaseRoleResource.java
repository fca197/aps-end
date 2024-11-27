package com.olivia.peanut.base.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 角色资源表(BaseRoleResource)表实体类
 *
 * @author peanut
 * @since 2024-08-09 15:42:36
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("base_role_resource")
public class BaseRoleResource extends BaseEntity {

  /***
   *  角色ID
   */
  private Long roleId;
  /***
   *  菜单ID
   */
  private Long resourceId;

}

