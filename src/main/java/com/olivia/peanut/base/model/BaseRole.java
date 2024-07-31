package com.olivia.peanut.base.model;


import java.time.LocalDate;
import java.time.LocalDateTime;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 角色表(BaseRole)表实体类
 *
 * @author peanut
 * @since 2024-07-31 14:33:35
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("base_role")
public class BaseRole extends BaseEntity {

  /***
   *  角色编码
   */
  private String roleCode;
  /***
   *  角色名称
   */
  private String roleName;
  /***
   *  角色组
   */
  private Long roleGroupId;

}

