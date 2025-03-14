package com.olivia.peanut.base.api.entity.baseDept;


import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 部门表(BaseDept)查询对象返回
 *
 * @author peanut
 * @since 2024-07-31 14:33:31
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseDeptDto extends BaseEntityDto {

  /***
   *  部门编码
   */
  @NotBlank(message = "部门编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String deptCode;
  /***
   *  部门名称
   */
  @NotBlank(message = "部门名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String deptName;
  /***
   *  父部门ID
   */
  @NotNull(message = "父部门ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long parentId;
  /***
   *  部门路径
   */
  @NotBlank(message = "部门路径不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String path;

}


