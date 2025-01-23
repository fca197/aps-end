package com.olivia.peanut.base.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 用户角色表(BaseUserRole)表实体类
 *
 * @author peanut
 * @since 2024-07-31 14:36:03
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("base_user_role")
public class BaseUserRole extends BaseEntity {

  /***
   *  用户ID
   */
  private Long userId;
  /***
   *  角色ID
   */
  private Long roleId;

}

