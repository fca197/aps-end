package com.olivia.peanut.base.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 部门表(BaseDept)表实体类
 *
 * @author peanut
 * @since 2024-07-31 14:33:30
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("base_dept")
public class BaseDept extends BaseEntity {

  /***
   *  部门编码
   */
  private String deptCode;
  /***
   *  部门名称
   */
  private String deptName;
  /***
   *  父部门ID
   */
  private Long parentId;
  /***
   *  部门路径
   */
  private String path;

}

