package com.olivia.peanut.base.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 用户角色资源表(BaseUserResource)表实体类
 *
 * @author peanut
 * @since 2024-08-09 16:26:40
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("base_user_resource")
public class BaseUserResource extends BaseEntity {

  /***
   *  角色ID
   */
  private Long userId;
  /***
   *  资源ID
   */
  private Long resourceId;

}

