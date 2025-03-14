package com.olivia.peanut.base.api.entity.baseUserDept;


import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户部门表(BaseUserDept)查询对象返回
 *
 * @author peanut
 * @since 2024-07-31 14:36:02
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseUserDeptDto extends BaseEntityDto {

  /***
   *  用户ID
   */
  @NotNull(message = "用户ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long userId;
  /***
   *  部门ID
   */
  @NotNull(message = "部门ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long deptId;

}


