package com.olivia.peanut.base.model;


import java.time.LocalDate;
import java.time.LocalDateTime;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 角色组资源表(BaseRoleGroupResource)表实体类
 *
 * @author peanut
 * @since 2024-08-09 15:42:34
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("base_role_group_resource")
public class BaseRoleGroupResource extends BaseEntity {

  /***
   *  角色ID
   */
  private Long roleGroupId;
  /***
   *  资源ID
   */
  private Long resourceId;

}

