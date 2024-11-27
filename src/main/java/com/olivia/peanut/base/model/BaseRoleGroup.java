package com.olivia.peanut.base.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 角色组表(BaseRoleGroup)表实体类
 *
 * @author peanut
 * @since 2024-07-31 14:33:36
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("base_role_group")
public class BaseRoleGroup extends BaseEntity {

  /***
   *  角色组编码
   */
  private String roleGroupCode;
  /***
   *  角色组名称
   */
  private String roleGroupName;

}

