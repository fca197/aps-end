package com.olivia.peanut.base.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 用户部门表(BaseUserDept)表实体类
 *
 * @author peanut
 * @since 2024-07-31 14:36:01
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("base_user_dept")
public class BaseUserDept extends BaseEntity {

  /***
   *  用户ID
   */
  private Long userId;
  /***
   *  部门ID
   */
  private Long deptId;

}

